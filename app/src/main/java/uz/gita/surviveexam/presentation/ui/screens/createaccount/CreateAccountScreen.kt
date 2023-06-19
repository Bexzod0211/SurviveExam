package uz.gita.surviveexam.presentation.ui.screens.createaccount

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

class CreateAccountScreen : AndroidScreen() {
    
    @Composable
    override fun Content() {
        val viewModel: CreateAccountContract.ViewModel = getViewModel<CreateAccountViewModel>()

        val context = LocalContext.current

        viewModel.collectSideEffect {
            when(it){
                is CreateAccountContract.SideEffect.Toast->{
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        ScreenContent(uiState = viewModel.collectAsState(), onEventDispatcher = viewModel::onEventDispatcher)

    }
}

@Composable
private fun ScreenContent(
    uiState:State<CreateAccountContract.UiState>,
    onEventDispatcher:(CreateAccountContract.Intent)->Unit
) {
    SignContent(
        textSubmitBtn = "CREATE ACCOUNT",
        switchText = "Sign in",
        onSubmitClick = {email,password->
            onEventDispatcher.invoke(CreateAccountContract.Intent.CreateAccountBtnClicked(email, password))
    },
    onTextClick = {
        onEventDispatcher.invoke(CreateAccountContract.Intent.SignInBtnClicked)
    })
}

@Preview
@Composable
private fun ScreenPreview() {
    ScreenContent(
         remember {
             mutableStateOf(CreateAccountContract.UiState)
         }
    ){

    }
}