package uz.gita.surviveexam.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.ProductData

interface CartUseCase {
    fun getAllProductsByCurrentUser():Flow<Result<List<ProductData>>>
}