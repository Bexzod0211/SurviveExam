package uz.gita.surviveexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.surviveexam.presentation.usecase.AddProductUseCase
import uz.gita.surviveexam.presentation.usecase.CartUseCase
import uz.gita.surviveexam.presentation.usecase.CreateAccountUseCase
import uz.gita.surviveexam.presentation.usecase.HomeUseCase
import uz.gita.surviveexam.presentation.usecase.OnBoardingUseCase
import uz.gita.surviveexam.presentation.usecase.SignInUseCase
import uz.gita.surviveexam.presentation.usecase.impl.AddProductUseCaseImpl
import uz.gita.surviveexam.presentation.usecase.impl.CartUseCaseImpl
import uz.gita.surviveexam.presentation.usecase.impl.CreateAccountUseCaseImpl
import uz.gita.surviveexam.presentation.usecase.impl.HomeUseCaseImpl
import uz.gita.surviveexam.presentation.usecase.impl.OnBoardingUseCaseImpl
import uz.gita.surviveexam.presentation.usecase.impl.SignInUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindOnBoardingUseCase(impl:OnBoardingUseCaseImpl):OnBoardingUseCase

    @Binds
    fun bindSignInUseCase(impl:SignInUseCaseImpl):SignInUseCase

    @Binds
    fun bindCreateAccountUseCase(impl:CreateAccountUseCaseImpl):CreateAccountUseCase

    @Binds
    fun bindHomeUseCase(impl:HomeUseCaseImpl):HomeUseCase

    @Binds
    fun bindCartUseCase(impl:CartUseCaseImpl):CartUseCase

    @Binds
    fun bindAddProductUseCase(impl:AddProductUseCaseImpl):AddProductUseCase
}