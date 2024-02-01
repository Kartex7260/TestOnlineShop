package kanti.testonlineshop.ui.components.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.PagingIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LargeProductImages(
    modifier: Modifier = Modifier,
    images: List<Any> = listOf()
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val pagerState = rememberPagerState { images.size }
    HorizontalPager(
        modifier = Modifier.weight(1f),
        state = pagerState
    ) { index ->
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .memoryCacheKey(images[index].toString())
            .memoryCachePolicy(CachePolicy.ENABLED)
            .lifecycle(LocalLifecycleOwner.current)
            .data(images[index])
            .build()
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
    Spacer(modifier = Modifier.height(16.dp))

    PagingIndicator(
        modifier = Modifier,
        count = images.size,
        select = pagerState.currentPage,
        spaceBetween = 8.dp,
        dotDiameter = 6.dp
    )
}

@Preview
@Composable
private fun PreviewLargeProductImages() {
    LargeProductImages(
        modifier = Modifier.height(200.dp),
        images = listOf(
            R.drawable.d3,
            R.drawable.d5,
            R.drawable.d4
        )
    )
}