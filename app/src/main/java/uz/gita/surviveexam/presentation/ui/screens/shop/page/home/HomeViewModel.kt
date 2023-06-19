package uz.gita.surviveexam.presentation.ui.screens.shop.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.surviveexam.presentation.usecase.HomeUseCase
import uz.gita.surviveexam.utils.myLog
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
):HomeContract.ViewModel,ViewModel(){

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when(intent){
            HomeContract.Intent.LoadAllData->{
                intent {
                    useCase.getAllProducts()
                        .onEach {result ->
                            result.onSuccess {
//                                myLog("onSuccess $it")
                                reduce {
                                    HomeContract.UiState.AllProducts(it)
                                }
                            }
                            result.onFailure {
                                myLog(it.message?:"")
                            }

                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    override val container =  container<HomeContract.UiState, HomeContract.SideEffect>(HomeContract.UiState.AllProducts())

}