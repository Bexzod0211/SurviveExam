package uz.gita.surviveexam.presentation.ui.screens.shop.page.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.surviveexam.presentation.usecase.CartUseCase
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val useCase: CartUseCase,
    private val direction:CartContract.Direction
) : CartContract.ViewModel, ViewModel() {
    override fun onEventDispatcher(intent: CartContract.Intent) {
        when (intent) {
            CartContract.Intent.LoadAllData -> {
                intent {
                    useCase.getAllProductsByCurrentUser()
                        .onEach { result ->
                            result.onSuccess {
                                reduce {
                                    CartContract.UiState.AllMyProducts(it)
                                }
                            }
                            result.onFailure {
                                postSideEffect(CartContract.SideEffect.Toast(it.message?:""))
                            }
                        }
                        .launchIn(viewModelScope)
                }
            }

            CartContract.Intent.AddProductClicked->{
                viewModelScope.launch {
                    direction.openAddProductScreen()
                }
            }
        }
    }

    override val container = container<CartContract.UiState, CartContract.SideEffect>(CartContract.UiState.AllMyProducts())
}