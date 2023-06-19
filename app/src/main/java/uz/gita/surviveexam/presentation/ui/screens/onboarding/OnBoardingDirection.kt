package uz.gita.surviveexam.presentation.ui.screens.onboarding

import uz.gita.surviveexam.navigation.AppNavigator
import uz.gita.surviveexam.presentation.ui.screens.signin.SignInScreen
import uz.gita.surviveexam.utils.myLog
import javax.inject.Inject

class OnBoardingDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : OnBoardingContract.Direction {
    override suspend fun navigateToSignInScreen() {
        myLog("navigateToSignInScreen in OnBoardingDirection")
        appNavigator.replace(SignInScreen())
    }
}