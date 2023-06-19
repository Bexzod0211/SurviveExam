package uz.gita.surviveexam.presentation.ui.screens.createaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.surviveexam.presentation.ui.screens.signin.SignInContract
import uz.gita.surviveexam.presentation.usecase.CreateAccountUseCase
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val useCase: CreateAccountUseCase,
    private val direction:CreateAccountContract.Direction
):CreateAccountContract.ViewModel,ViewModel(){

    override fun onEventDispatcher(intent: CreateAccountContract.Intent) {
        when(intent){
            is CreateAccountContract.Intent.CreateAccountBtnClicked->{
                if (!intent.email.contains("@") || intent.email.length < 10){
                    intent {
                        postSideEffect(CreateAccountContract.SideEffect.Toast("Enter a valid email"))
                    }
                    return
                }
                if (intent.password.length < 6){
                    intent {
                        postSideEffect(CreateAccountContract.SideEffect.Toast("Password must contain at least 6 characters"))
                    }
                    return
                }
                useCase.createAccount(intent.email,intent.password)
                    .onEach {result ->
                        result.onSuccess {
                            intent {
                                postSideEffect(CreateAccountContract.SideEffect.Toast(it))
                            }
                            direction.openMainScreen()
                        }
                        result.onFailure {
                            intent {
                                postSideEffect(CreateAccountContract.SideEffect.Toast(it.message?:""))
                            }
                        }
                    }
                    .launchIn(viewModelScope)
            }
            CreateAccountContract.Intent.SignInBtnClicked->{
                viewModelScope.launch {
                    direction.openSignInScreen()
                }
            }
        }
    }

    override val container = container<CreateAccountContract.UiState, CreateAccountContract.SideEffect>(CreateAccountContract.UiState)
}