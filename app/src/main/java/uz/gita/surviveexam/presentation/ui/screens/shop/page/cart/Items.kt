package uz.gita.surviveexam.presentation.ui.screens.shop.page.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import uz.gita.surviveexam.R
import uz.gita.surviveexam.data.model.ProductData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemMyProduct(product: ProductData) {
    Card(
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 24.dp
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(24.dp)
            )
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults
            .cardColors(
                containerColor = Color.White
            )
    )
    {
        Row {
            GlideImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp,
                        vertical = 20.dp
                    )
                    .size(120.dp, 120.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = "$${product.price}",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    text = "Size: ${product.size}",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalAlignment = Alignment
                    .CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_remove),
                    contentDescription = null
                )
                Text(
                    text = product.amount,
                    color = Color.Gray,
                    fontSize = 20.sp
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.ic_add),
                    contentDescription = null
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ItemPreview() {
    Surface(modifier = Modifier.background(Color(0xFF9C9C9C))) {
//        ItemMyProduct(ProductData("","225.00","Lorem","US 7","1"))
        ItemMyProduct(ProductData("","","","",""))
    }
}