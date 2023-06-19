package uz.gita.surviveexam.presentation.ui.screens.shop.page.cart

import org.orbitmvi.orbit.ContainerHost
import uz.gita.surviveexam.data.model.ProductData

interface CartContract {
    sealed interface Intent {
        object LoadAllData:Intent
        object AddProductClicked:Intent
    }

    sealed interface SideEffect {
        class Toast(val message:String):SideEffect
    }

    sealed interface UiState {
        class AllMyProducts(val products:List<ProductData> = listOf()):UiState
    }

    interface ViewModel : ContainerHost<UiState,SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }

    interface Direction {
        suspend fun openAddProductScreen()
    }
}
