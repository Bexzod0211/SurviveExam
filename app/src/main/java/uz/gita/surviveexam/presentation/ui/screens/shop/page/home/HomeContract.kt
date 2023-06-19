package uz.gita.surviveexam.presentation.ui.screens.shop.page.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.surviveexam.data.model.CategoryData

interface HomeContract {
    sealed interface Intent {
        object LoadAllData:Intent
    }

    sealed interface UiState {
        class AllProducts(val products:List<CategoryData> = listOf()):UiState
    }

    sealed interface SideEffect {

    }

    interface ViewModel : ContainerHost<UiState,SideEffect>{
        fun onEventDispatcher(intent: Intent)
    }
}