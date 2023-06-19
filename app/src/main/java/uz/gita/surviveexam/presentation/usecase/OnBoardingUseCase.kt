package uz.gita.surviveexam.presentation.usecase

import kotlinx.coroutines.flow.Flow

interface OnBoardingUseCase {
    fun isFirstRun(): Flow<Result<Boolean>>
    fun setIsFirstRun(value:Boolean): Flow<Result<Unit>>
}