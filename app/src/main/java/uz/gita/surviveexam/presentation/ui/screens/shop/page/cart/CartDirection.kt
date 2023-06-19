package uz.gita.surviveexam.presentation.ui.screens.shop.page.cart

import uz.gita.surviveexam.navigation.AppNavigator
import uz.gita.surviveexam.presentation.ui.screens.addproduct.AddProductScreen
import javax.inject.Inject

class CartDirection @Inject constructor(
    private val appNavigator: AppNavigator
) :CartContract.Direction{
    override suspend fun openAddProductScreen() {
        appNavigator.navigateTo(AddProductScreen())
    }

}