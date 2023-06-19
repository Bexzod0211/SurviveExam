package uz.gita.surviveexam.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.tasks.await
import uz.gita.surviveexam.data.local.LocalStorage
import uz.gita.surviveexam.data.model.CategoryData
import uz.gita.surviveexam.data.model.ProductData
import uz.gita.surviveexam.utils.myLog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage,
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : AppRepository {
    val scope = CoroutineScope(Dispatchers.IO)

    override fun isFirstRun(): Flow<Result<Boolean>> = flow {
        emit(Result.success(localStorage.isFirstRun))
    }

    override fun setIsFirstRun(value: Boolean): Flow<Result<Unit>> = flow {
        localStorage.isFirstRun = value
        emit(Result.success(Unit))
    }

    override fun createAccount(email: String, password: String): Flow<Result<String>> = callbackFlow {
        myLog("email : $email password: $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(Result.success("You have create an account successfully"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
                myLog(it.message ?: "")
            }

        awaitClose()
    }

    override fun signIn(email: String, password: String): Flow<Result<String>> = callbackFlow {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(Result.success("You have signed in successfully"))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }

    override fun hasSignedIn(): Flow<Result<Boolean>> = flow {
        emit(Result.success(auth.currentUser != null))
    }

    override fun getAllProducts(): Flow<Result<List<CategoryData>>> = callbackFlow {
        val list = mutableListOf<CategoryData>()
        getAllCategories().onEach { result ->
            result.onSuccess { categories ->
                categories.forEach {
                    list.add(CategoryData(it, getProductsByCategory(it)))
                }
                trySend(Result.success(list))
            }
            result.onFailure {
                trySend(Result.failure(it))
                myLog("get Categories :${it.message}")
            }

        }.launchIn(scope)

        awaitClose()
    }

    private suspend fun getProductsByCategory(name: String): List<ProductData> {
        val list = mutableListOf<ProductData>()
        val documents = fireStore.collection("products")
            .whereEqualTo("category", name)
            .get()
            .await()

        documents.forEach {
            list.add(
                ProductData(
                    image = it.get("image") as String,
                    price = it.get("price") as String,
                    name = it.get("name") as String
                )
            )
        }

        return list
    }

    override fun getAllCategories(): Flow<Result<List<String>>> = callbackFlow {
        val list = mutableListOf<String>()
        fireStore.collection("category")
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach {
                    list.add(it.get("name") as String)
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
                myLog(it.message ?: "")
            }
        awaitClose()
    }

    override fun getAllProductsByCurrentUser(): Flow<Result<List<ProductData>>> = callbackFlow {
        val list = mutableListOf<ProductData>()
        fireStore.collection("products")
            .whereEqualTo("userUid", auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach {
                    list.add(
                        ProductData(
                            image = it.get("image") as String,
                            price = it.get("price") as String,
                            name = it.get("name") as String,
                            size = it.get("size") as String,
                            amount = it.get("amount") as String
                        )
                    )
                }
                trySend(Result.success(list))
            }
            .addOnFailureListener {
                trySend(Result.failure(it))
            }

        awaitClose()
    }

    override fun addProduct(product: ProductData, category: String): Flow<Result<String>> = callbackFlow {
        var isExist = false

        getAllCategories().onEach { result ->
            result.onSuccess {
                it.forEach { string ->
                    if (category == string) {
                        isExist = true
                        return@forEach
                    }
                }
                result.onFailure {
                    myLog("getAllCategories error :${it.message}")
                }

                val productMap = hashMapOf(
                    "size" to product.size,
                    "amount" to product.amount,
                    "price" to product.price,
                    "image" to sneakersImgUrl,
                    "name" to product.name,
                    "category" to category,
                    "userUid" to auth.currentUser?.uid
                )

                if (!isExist) {
                    val map = hashMapOf("name" to category)

                    fireStore.collection("category")
                        .document(category)
                        .set(map)
                        .addOnSuccessListener {
                            fireStore.collection("products")
                                .document()
                                .set(productMap)
                                .addOnSuccessListener {
                                    trySend(Result.success("Product has been added successfully"))
                                }
                                .addOnFailureListener { e ->
                                    trySend(Result.failure(e))
                                }
                        }
                } else {
                    fireStore.collection("products")
                        .document()
                        .set(productMap)
                        .addOnSuccessListener {
                            trySend(Result.success("Product has been added successfully"))
                        }
                        .addOnFailureListener { e ->
                            trySend(Result.failure(e))
                        }
                }

            }
        }.launchIn(scope)


        awaitClose()
    }

    private val sneakersImgUrl =
        "https://images.unsplash.com/photo-1552346154-21d32810aba3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8c25lYWtlcnN8ZW58MHx8MHx8fDA%3D&w=1000&q=80"

}