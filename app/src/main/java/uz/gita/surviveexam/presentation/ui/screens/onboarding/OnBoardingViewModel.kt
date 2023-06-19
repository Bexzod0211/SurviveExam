package uz.gita.surviveexam.presentation.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.surviveexam.presentation.usecase.OnBoardingUseCase
import uz.gita.surviveexam.utils.myLog
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val direction: OnBoardingContract.Direction,
    private val useCase: OnBoardingUseCase
) : OnBoardingContract.ViewModel, ViewModel() {

    init {
        useCase.isFirstRun().onEach { result ->
            result.onSuccess {
                if (!it) {
                    direction.navigateToSignInScreen()
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onEventDispatcher(intent: OnBoardingContract.Intent) {
        when (intent) {
            OnBoardingContract.Intent.OnStartClicked -> {
                useCase.setIsFirstRun(false).onEach {

                }.launchIn(viewModelScope)
                viewModelScope.launch {
                    direction.navigateToSignInScreen()
                }
            }

            OnBoardingContract.Intent.CheckFirstUse -> {
                useCase.isFirstRun().onEach { result ->
                    result.onSuccess {
                        myLog("$it")
                        if (it == false) {
                            direction.navigateToSignInScreen()
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    override val container = container<OnBoardingContract.UiState, OnBoardingContract.SideEffect>(OnBoardingContract.UiState.Loading)
}