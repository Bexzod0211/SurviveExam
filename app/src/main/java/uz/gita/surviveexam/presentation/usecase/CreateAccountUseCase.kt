package uz.gita.surviveexam.presentation.usecase

import kotlinx.coroutines.flow.Flow

interface CreateAccountUseCase {
    fun createAccount(email:String,password:String):Flow<Result<String>>
}