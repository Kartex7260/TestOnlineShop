package kanti.testonlineshop.ui.components.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import kanti.testonlineshop.ui.components.PagingIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SmallProductImages(
    modifier: Modifier = Modifier,
    images: List<Any> = listOf()
) = Box(
    modifier = modifier
) {
    val pagerState = rememberPagerState { images.size }
    HorizontalPager(
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
    PagingIndicator(
        modifier = Modifier.align(Alignment.BottomCenter),
        count = images.size,
        select = pagerState.currentPage
    )
}

@Preview
@Composable
fun PreviewSmallProductImages() {
    SmallProductImages(
        modifier = Modifier.size(120.dp)
    )
}