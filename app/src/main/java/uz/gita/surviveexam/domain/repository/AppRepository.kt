package uz.gita.surviveexam.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.CategoryData
import uz.gita.surviveexam.data.model.ProductData

interface AppRepository {
    fun isFirstRun():Flow<Result<Boolean>>
    fun setIsFirstRun(value:Boolean):Flow<Result<Unit>>
    fun createAccount(email:String,password:String):Flow<Result<String>>
    fun signIn(email: String,password: String):Flow<Result<String>>
    fun hasSignedIn():Flow<Result<Boolean>>
    fun getAllProducts():Flow<Result<List<CategoryData>>>
    fun getAllCategories():Flow<Result<List<String>>>
    fun getAllProductsByCurrentUser():Flow<Result<List<ProductData>>>
    fun addProduct(product:ProductData,category:String):Flow<Result<String>>
}