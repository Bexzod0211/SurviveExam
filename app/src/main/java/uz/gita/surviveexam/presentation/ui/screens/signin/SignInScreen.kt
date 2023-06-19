package uz.gita.surviveexam.presentation.ui.screens.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.surviveexam.presentation.ui.screens.SignContent

class SignInScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel:SignInContract.ViewModel = getViewModel<SignInViewModel>()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when(it){
                is SignInContract.SideEffect.Toast->{
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
        ScreenContent(uiState = viewModel.collectAsState(), onEvenDispatcher = viewModel::onEventDispatcher )
    }
}
@Composable
private fun ScreenContent(
    uiState:State<SignInContract.UiState>,
    onEvenDispatcher:(SignInContract.Intent)->Unit
){
    onEvenDispatcher.invoke(SignInContract.Intent.CheckCurrentUser)
    SignContent(
        textSubmitBtn = "SIGN IN",
        switchText = "Create a new account",
        onSubmitClick = {email,password->
            onEvenDispatcher.invoke(SignInContract.Intent.SignInBtnClicked(email, password))
        }
    , onTextClick = {
        onEvenDispatcher.invoke(SignInContract.Intent.CreateAccountBtnClicked)
        })
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview(){
    ScreenContent(
        remember {
            mutableStateOf(SignInContract.UiState)
        }
    ){

    }
}
