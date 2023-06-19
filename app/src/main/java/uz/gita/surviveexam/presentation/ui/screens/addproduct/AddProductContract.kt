package uz.gita.surviveexam.presentation.ui.screens.addproduct

import org.orbitmvi.orbit.ContainerHost
import uz.gita.surviveexam.data.model.ProductData

interface AddProductContract {
    sealed interface Intent {
        class AddBtnClicked(val product:ProductData,val category:String):Intent
    }

    sealed interface SideEffect {
        class Toast(val message:String):SideEffect
    }

    object UiState {

    }

    interface ViewModel : ContainerHost<UiState,SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun back()
    }
}