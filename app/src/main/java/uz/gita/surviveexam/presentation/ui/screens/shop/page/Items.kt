package uz.gita.surviveexam.presentation.ui.screens.shop.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uz.gita.surviveexam.data.model.CategoryData
import uz.gita.surviveexam.data.model.ProductData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemProduct(product: ProductData) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .size(150.dp, 150.dp)
                .clip(RoundedCornerShape(12.dp)),
            elevation = CardDefaults
                .cardElevation(
                    defaultElevation = 4.dp
                )
        ) {
            GlideImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp, 150.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "$${product.price}",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 8.dp)
        )

        Text(
            text = product.name,
            color = Color.Gray,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun ItemContentPreview() {
    ItemProduct(
        ProductData(
            "https://images.unsplash.com/photo-1514989940723-e8e51635b782?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            "239.80",
            "Nike Air Max 90"
        )
    )
}

@Composable
fun ItemCategory(category: CategoryData) {
    Column {
        Text(
            text = category.categoryName,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 24.dp
                ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            category.list.forEach {
                item {
                    ItemProduct(product = it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryContentPreview() {
    ItemCategory(category = CategoryData("Airmax", listOf()))
}