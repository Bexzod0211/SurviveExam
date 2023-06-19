package uz.gita.surviveexam.presentation.ui.screens.addproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.surviveexam.presentation.usecase.AddProductUseCase
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val useCase: AddProductUseCase,
    private val direction:AddProductContract.Direction
) : AddProductContract.ViewModel, ViewModel() {
    override fun onEventDispatcher(intent: AddProductContract.Intent) {
        when (intent) {
            is AddProductContract.Intent.AddBtnClicked->{
                if (intent.product.name == ""){
                    intent {
                        postSideEffect(AddProductContract.SideEffect.Toast("Name can't be empty"))
                    }
                    return
                }
                if (intent.product.price == ""){
                    intent {
                        postSideEffect(AddProductContract.SideEffect.Toast("Price can't be empty"))
                    }
                    return
                }
                if (intent.product.size == ""){
                    intent {
                        postSideEffect(AddProductContract.SideEffect.Toast("Size can't be empty"))
                    }
                    return
                }
                if (intent.product.amount == ""){
                    intent {
                        postSideEffect(AddProductContract.SideEffect.Toast("Amount can't be empty"))
                    }
                    return
                }
                if (intent.category == ""){
                    intent {
                        postSideEffect(AddProductContract.SideEffect.Toast("Category can't be empty"))
                    }
                    return
                }

                useCase.addProduct(intent.product,intent.category)
                    .onEach {result ->
                    result.onSuccess {
                        intent {
                            postSideEffect(AddProductContract.SideEffect.Toast(it))
                        }
                        direction.back()
                    }
                        result.onFailure {
                            intent {
                                postSideEffect(AddProductContract.SideEffect.Toast(it.message?:""))
                            }
                        }
                    }
                    .launchIn(viewModelScope)
            }
        }

    }

    override val container = container<AddProductContract.UiState, AddProductContract.SideEffect>(AddProductContract.UiState)
}