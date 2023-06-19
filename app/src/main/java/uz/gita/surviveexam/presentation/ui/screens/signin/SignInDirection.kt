package uz.gita.surviveexam.presentation.ui.screens.signin

import uz.gita.surviveexam.navigation.AppNavigator
import uz.gita.surviveexam.presentation.ui.screens.createaccount.CreateAccountScreen
import uz.gita.surviveexam.presentation.ui.screens.shop.MainScreen
import javax.inject.Inject

class SignInDirection @Inject constructor(
    private val appNavigator: AppNavigator
): SignInContract.Direction {
    override suspend fun openCreateAccountScreen() {
        appNavigator.replace(CreateAccountScreen())
    }

    override suspend fun openMainScreen() {
        appNavigator.replace(MainScreen())
    }
}