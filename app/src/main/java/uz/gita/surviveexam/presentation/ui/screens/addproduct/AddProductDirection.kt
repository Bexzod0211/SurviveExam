package uz.gita.surviveexam.presentation.ui.screens.addproduct

import uz.gita.surviveexam.navigation.AppNavigator
import javax.inject.Inject

class AddProductDirection @Inject constructor(
    private val appNavigator: AppNavigator
): AddProductContract.Direction{
    override suspend fun back() {
        appNavigator.pop()
    }
}