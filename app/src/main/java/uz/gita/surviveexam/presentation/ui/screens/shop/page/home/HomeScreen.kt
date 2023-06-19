package uz.gita.surviveexam.presentation.ui.screens.shop.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.surviveexam.R
import uz.gita.surviveexam.presentation.ui.screens.shop.page.ItemCategory

object HomeScreen : Tab {
    override val options: TabOptions
    @Composable
        get()  {
            return TabOptions(
                0u,
                "Home",
                icon = painterResource(id = R.drawable.ic_dashboard)
            )
        }

    @Composable
    override fun Content() {
        val viewModel:HomeContract.ViewModel = getViewModel<HomeViewModel>()

        ScreenContent(uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher)
    }
}

@Composable
private fun ScreenContent(
    uiState:State<HomeContract.UiState>,
    onEventDispatcher:(HomeContract.Intent)->Unit
){
    onEventDispatcher.invoke(HomeContract.Intent.LoadAllData)
    val state = uiState.value

    when(state){
        is HomeContract.UiState.AllProducts->{
            Column {
                Text(
                    text = "ALL PRODUCTS",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,

                    )
                LazyColumn {
                    state.products.forEach {
                        item {
                            ItemCategory(category = it)
                        }
                    }
                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun ScreenPreview(){
    ScreenContent(uiState = remember {
        mutableStateOf(HomeContract.UiState.AllProducts())
    }){

    }
}