package uz.gita.surviveexam.presentation.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.ProductData
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.presentation.usecase.CartUseCase
import javax.inject.Inject

class CartUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : CartUseCase {
    override fun getAllProductsByCurrentUser(): Flow<Result<List<ProductData>>> {
        return repository.getAllProductsByCurrentUser()
    }
}