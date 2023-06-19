package uz.gita.surviveexam.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.CategoryData

interface HomeUseCase {
    fun getAllProducts():Flow<Result<List<CategoryData>>>
}