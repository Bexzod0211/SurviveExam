package uz.gita.surviveexam.presentation.ui.screens.createaccount

import org.orbitmvi.orbit.ContainerHost

interface CreateAccountContract {
    sealed interface Intent {
        class CreateAccountBtnClicked(val email:String,val password:String):Intent
        object SignInBtnClicked:Intent
    }

    object UiState {

    }

    sealed interface SideEffect {
        class Toast(val message:String):SideEffect
    }

    interface Direction {
        suspend fun openMainScreen()
        suspend fun openSignInScreen()
    }

    interface ViewModel : ContainerHost<UiState,SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }
}