package uz.gita.surviveexam.presentation.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.surviveexam.data.model.ProductData

interface AddProductUseCase {
    fun addProduct(product:ProductData,category:String):Flow<Result<String>>
}