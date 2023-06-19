package uz.gita.surviveexam.presentation.ui.screens.onboarding

import org.orbitmvi.orbit.ContainerHost

interface OnBoardingContract {
    sealed interface Intent {
        object OnStartClicked:Intent
        object CheckFirstUse:Intent
    }

    interface Direction {
        suspend fun navigateToSignInScreen()
    }

    sealed interface SideEffect {

    }

    sealed interface UiState {
        object Loading:UiState
    }

    interface ViewModel : ContainerHost<UiState,SideEffect>{
        fun onEventDispatcher(intent:Intent)
    }
}