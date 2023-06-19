package uz.gita.surviveexam.presentation.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.presentation.usecase.OnBoardingUseCase
import javax.inject.Inject

class OnBoardingUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : OnBoardingUseCase{
    override fun isFirstRun(): Flow<Result<Boolean>> {
        return repository.isFirstRun()
    }

    override fun setIsFirstRun(value: Boolean): Flow<Result<Unit>> {
        return repository.setIsFirstRun(value)
    }
}