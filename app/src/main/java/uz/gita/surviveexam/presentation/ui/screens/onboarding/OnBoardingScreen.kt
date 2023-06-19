package uz.gita.surviveexam.presentation.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.surviveexam.R

class OnBoardingScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel:OnBoardingContract.ViewModel = getViewModel<OnBoardingViewModel>()

        ScreenContent(uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher)

    }
}

@Composable
private fun ScreenContent(
    uiState:State<OnBoardingContract.UiState>,
    onEventDispatcher:(OnBoardingContract.Intent)->Unit
) {
    onEventDispatcher.invoke(OnBoardingContract.Intent.CheckFirstUse)

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Image(
                    painter = painterResource(id = R.drawable.shop_cart),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(id = R.string.lorem_title),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = stringResource(id = R.string.lorem_description),
                    color = Color.Black,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .padding(
                            top = 50.dp,
                            bottom = 100.dp
                        )
                        .background(Color(0xFF667080), shape = CircleShape)
                        .size(180.dp, 60.dp)
                        .clickable {
                            onEventDispatcher.invoke(OnBoardingContract.Intent.OnStartClicked)
                        }
                    ,
                ) {
                    Column(modifier = Modifier
                        .size(180.dp,60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "START",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp)
                    }
                }
        }
    }
}

@Preview
@Composable
private fun ContentPreview() {
//    ScreenContent()
}