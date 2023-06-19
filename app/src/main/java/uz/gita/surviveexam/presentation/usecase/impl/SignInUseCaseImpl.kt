package uz.gita.surviveexam.presentation.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.presentation.usecase.SignInUseCase
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val repository: AppRepository
): SignInUseCase{

    override fun signIn(email: String, password: String): Flow<Result<String>> {
        return repository.signIn(email, password)
    }

    override fun hasSignedIn(): Flow<Result<Boolean>> {
        return repository.hasSignedIn()
    }

}