package uz.gita.surviveexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.surviveexam.presentation.ui.screens.addproduct.AddProductContract
import uz.gita.surviveexam.presentation.ui.screens.addproduct.AddProductDirection
import uz.gita.surviveexam.presentation.ui.screens.createaccount.CreateAccountContract
import uz.gita.surviveexam.presentation.ui.screens.createaccount.CreateAccountDirection
import uz.gita.surviveexam.presentation.ui.screens.onboarding.OnBoardingContract
import uz.gita.surviveexam.presentation.ui.screens.onboarding.OnBoardingDirection
import uz.gita.surviveexam.presentation.ui.screens.shop.page.cart.CartContract
import uz.gita.surviveexam.presentation.ui.screens.shop.page.cart.CartDirection
import uz.gita.surviveexam.presentation.ui.screens.signin.SignInContract
import uz.gita.surviveexam.presentation.ui.screens.signin.SignInDirection

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun bindOnBoardingDirection(impl:OnBoardingDirection):OnBoardingContract.Direction

    @Binds
    fun bindSignInDirection(impl:SignInDirection):SignInContract.Direction

    @Binds
    fun bindCreateAccountDirection(impl:CreateAccountDirection):CreateAccountContract.Direction

    @Binds
    fun bindAddProductDirection(impl:AddProductDirection):AddProductContract.Direction

    @Binds
    fun bindCartDirection(impl:CartDirection):CartContract.Direction
}