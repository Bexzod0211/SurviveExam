package uz.gita.surviveexam.presentation.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.CategoryData
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.presentation.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val repository: AppRepository
):HomeUseCase {
    override fun getAllProducts(): Flow<Result<List<CategoryData>>> {
        return repository.getAllProducts()
    }
}