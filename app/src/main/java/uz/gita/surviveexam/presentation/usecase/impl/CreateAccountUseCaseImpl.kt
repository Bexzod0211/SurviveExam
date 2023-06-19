package uz.gita.surviveexam.presentation.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.presentation.usecase.CreateAccountUseCase
import javax.inject.Inject

class CreateAccountUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) :CreateAccountUseCase{
    override fun createAccount(email: String, password: String): Flow<Result<String>> {
        return repository.createAccount(email, password)
    }
}