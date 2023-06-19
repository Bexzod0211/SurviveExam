package uz.gita.surviveexam.presentation.usecase

import kotlinx.coroutines.flow.Flow

interface SignInUseCase {
    fun signIn(email:String,password:String):Flow<Result<String>>
    fun hasSignedIn():Flow<Result<Boolean>>
}