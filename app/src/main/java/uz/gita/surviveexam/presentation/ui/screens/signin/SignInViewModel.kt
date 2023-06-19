package uz.gita.surviveexam.presentation.ui.screens.signin

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
import uz.gita.surviveexam.presentation.usecase.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val direction: SignInContract.Direction,
    private val useCase: SignInUseCase
) : SignInContract.ViewModel, ViewModel() {

    init {

    }

    override fun onEventDispatcher(intent: SignInContract.Intent) {
        when(intent){
            is SignInContract.Intent.SignInBtnClicked->{
                if (!intent.email.contains("@") || intent.email.length < 10){
                    intent {
                        postSideEffect(SignInContract.SideEffect.Toast("Enter a valid email"))
                    }
                    return
                }
                if (intent.password.length < 6){
                    intent {
                        postSideEffect(SignInContract.SideEffect.Toast("Password must contain at least 6 characters"))
                    }
                    return
                }
                useCase.signIn(intent.email,intent.password)
                    .onEach {result ->
                    result.onSuccess {
                        intent {
                            postSideEffect(SignInContract.SideEffect.Toast(it))
                        }
                        direction.openMainScreen()
                    }
                        result.onFailure {
                            intent {
                                postSideEffect(SignInContract.SideEffect.Toast(it.message?:""))
                            }
                        }

                    }
                    .launchIn(viewModelScope)
            }

            SignInContract.Intent.CreateAccountBtnClicked->{
                viewModelScope.launch {
                    direction.openCreateAccountScreen()
                }
            }

            SignInContract.Intent.CheckCurrentUser->{
                useCase.hasSignedIn().onEach { result ->
                    result.onSuccess {
                        if (it) {
                            direction.openMainScreen()
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }

    }

    override val container = container<SignInContract.UiState, SignInContract.SideEffect>(SignInContract.UiState)
}