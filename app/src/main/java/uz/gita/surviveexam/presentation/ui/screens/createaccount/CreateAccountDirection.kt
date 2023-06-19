package uz.gita.surviveexam.presentation.ui.screens.createaccount

import uz.gita.surviveexam.navigation.AppNavigator
import uz.gita.surviveexam.presentation.ui.screens.shop.MainScreen
import uz.gita.surviveexam.presentation.ui.screens.signin.SignInScreen
import javax.inject.Inject

class CreateAccountDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : CreateAccountContract.Direction{
    override suspend fun openMainScreen() {
        appNavigator.replace(MainScreen())
    }

    override suspend fun openSignInScreen() {
        appNavigator.replace(SignInScreen())
    }
}