package uz.gita.surviveexam.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.surviveexam.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignContent(textSubmitBtn:String,switchText:String,onSubmitClick:(String,String)->Unit,onTextClick:()->Unit) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Image(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = null,
        modifier = Modifier
            .padding(16.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Text(
                text = "Email",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Box(modifier = Modifier
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
                )) {
                BasicTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    decorationBox = { innerTextField ->
                        Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center) {
                            if (email.isEmpty()) {
                                Text(
                                    text = "example.user@gmail.com",
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


            Text(
                text = "Password",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 16.dp,
                    top = 32.dp)
            )
            Box(modifier = Modifier
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
                )) {
                BasicTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    decorationBox = { innerTextField ->
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center) {
                            if (email.isEmpty()) {
                                Text(
                                    text = "password",
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

//            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 50.dp
                    )
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFF667080), shape = CircleShape),
            ) {
                Column(modifier = Modifier
                    .matchParentSize()
                    .clickable {
                               onSubmitClick.invoke(email,password)
                    },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = textSubmitBtn,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp)
                }
            }

            Text(text = switchText,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
                .clickable {
                           onTextClick.invoke()
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignPreview() {
    SignContent("SIGN IN",
        "Create a new account",
    onSubmitClick = {email,password->

    }, onTextClick = {

        })
}