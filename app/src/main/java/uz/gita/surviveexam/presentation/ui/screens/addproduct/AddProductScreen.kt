package uz.gita.surviveexam.presentation.ui.screens.addproduct

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.surviveexam.data.model.ProductData

class AddProductScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val context = LocalContext.current

        val viewModel:AddProductContract.ViewModel = getViewModel<AddProductViewModel>()

        viewModel.collectSideEffect {
            when(it){
                is AddProductContract.SideEffect.Toast->{
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        ScreenContent(uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher)

    }
}

@Composable
private fun ScreenContent(
    uiState:State<AddProductContract.UiState>,
    onEventDispatcher:(AddProductContract.Intent)->Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var size by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }
    var category by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "ADD PRODUCT",
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
        TextAndTextField(text = "Name", fieldValue = name, placeHolder = "e.g. Converse", onValueChange = {
            name = it
        })

        TextAndTextField(text = "Price", fieldValue = price, placeHolder = "e.g. $225.00", onValueChange = {
            price = it
        })

        TextAndTextField(text = "Size", fieldValue = size, placeHolder = "e.g. US 7", onValueChange = {
            size = it
        })

        TextAndTextField(text = "Amount", fieldValue = amount, placeHolder = "e.g. 12", onValueChange = {
            amount = it
        })

        TextAndTextField(text = "Category", fieldValue = category, placeHolder = "e.g. AirMax", onValueChange ={
            category = it
        })

        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .size(250.dp, 70.dp)
                .align(Alignment.CenterHorizontally)
                .background(
                    color = Color(0xFF667080),
                    shape = RoundedCornerShape(50)
                )
                .clickable {
                    onEventDispatcher.invoke(AddProductContract.Intent.AddBtnClicked(
                        ProductData(image = "",
                        price = price,
                        name = name,
                        size = size,
                        amount = amount),
                        category))
                }
        ) {
            Column(modifier = Modifier
                .matchParentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "ADD TO BAG",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ScreenContent(
        remember {
            mutableStateOf(AddProductContract.UiState)
        }
    ){

    }
}

@Composable
private fun TextAndTextField(text: String, fieldValue: String, placeHolder: String, onValueChange: (String) -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .padding(
                top = 24.dp,
                start = 16.dp
            ),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    Box(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
            .fillMaxWidth()
            .height(60.dp)
            .background(
                Color(0xFFEEF1F4),
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        BasicTextField(
            value = fieldValue,
            onValueChange = {
                onValueChange.invoke(it)
            },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            textStyle = TextStyle(fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            decorationBox = { innerTextField ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (fieldValue.isEmpty()) {
                        Text(
                            text = placeHolder,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 32.dp)
                        )
                    }
                }
                innerTextField()
            })
    }
}
