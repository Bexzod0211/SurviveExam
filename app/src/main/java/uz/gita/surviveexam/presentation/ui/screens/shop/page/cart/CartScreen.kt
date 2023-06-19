package uz.gita.surviveexam.presentation.ui.screens.shop.page.cart

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.surviveexam.R
import uz.gita.surviveexam.presentation.ui.theme.SurviveExamTheme

object CartScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                1u,
                "Cart",
                icon = painterResource(id = R.drawable.ic_cart)
            )
        }

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel:CartContract.ViewModel = getViewModel<CartViewModel>()

        viewModel.collectSideEffect {
            when(it){
                is CartContract.SideEffect.Toast->{
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        ScreenContent(uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher)
    }
}

@Composable
private fun ScreenContent(
    uiState:State<CartContract.UiState>,
    onEventDispatcher:(CartContract.Intent)->Unit
) {

    val state = uiState.value
    onEventDispatcher.invoke(CartContract.Intent.LoadAllData)
    SurviveExamTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F3F3))) {
            Text(
                text = "MY PRODUCTS",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,

            )
            when(state){
                is CartContract.UiState.AllMyProducts->{
                    LazyColumn(modifier = Modifier
                        .padding(top = 50.dp)
                        .matchParentSize()) {
                        state.products.forEach {
                            item {
                                ItemMyProduct(product = it)
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .size(250.dp, 70.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color(0xFF667080),
                        shape = RoundedCornerShape(50)
                    )
                    .clickable {
                        onEventDispatcher.invoke(CartContract.Intent.AddProductClicked)
                    }
            ) {
                Column(modifier = Modifier
                    .matchParentSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "ADD PRODUCT",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
//    ScreenContent()
}
