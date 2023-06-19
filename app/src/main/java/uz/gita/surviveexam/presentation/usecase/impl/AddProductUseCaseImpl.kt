package uz.gita.surviveexam.presentation.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.ProductData
import uz.gita.surviveexam.domain.repository.AppRepository
import uz.gita.surviveexam.presentation.usecase.AddProductUseCase
import javax.inject.Inject

class AddProductUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : AddProductUseCase{
    override fun addProduct(product: ProductData,category: String): Flow<Result<String>> {
        return repository.addProduct(product,category)
    }

}