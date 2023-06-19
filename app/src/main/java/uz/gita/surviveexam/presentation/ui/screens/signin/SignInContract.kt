package uz.gita.surviveexam.presentation.ui.screens.signin

import org.orbitmvi.orbit.ContainerHost

interface SignInContract {

    sealed interface Intent {
        class SignInBtnClicked(val email:String,val password:String):Intent
        object CreateAccountBtnClicked:Intent
        object CheckCurrentUser:Intent
    }

    object UiState {

    }

    sealed interface SideEffect {
        class Toast(val message:String):SideEffect
    }

    interface ViewModel:ContainerHost<UiState,SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun openCreateAccountScreen()
        suspend fun openMainScreen()
    }
}