package kanti.testonlineshop.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.Feedback
import kanti.testonlineshop.ui.theme.element
import kanti.testonlineshop.ui.theme.textBlack

@Composable
fun RatingTag(
    modifier: Modifier = Modifier,
    feedback: Feedback,
    normalColor: Color = MaterialTheme.colors.textBlack
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    val ratingColor by remember {
        mutableStateOf(Color(0xFFF9A249))
    }
    Image(
        painter = painterResource(id = R.drawable.star),
        contentDescription = null,
        colorFilter = ColorFilter.tint(ratingColor),
        contentScale = ContentScale.None,
        modifier = Modifier.size(16.dp)
    )
    Spacer(modifier = Modifier.width(2.dp))
    Text(
        text = feedback.rating.toString(),
        style = MaterialTheme.typography.element,
        modifier = Modifier.padding(bottom = 2.dp),
        color = ratingColor
    )
    Spacer(modifier = Modifier.width(2.dp))
    Text(
        text = "(${feedback.count})",
        style = MaterialTheme.typography.element,
        modifier = Modifier.padding(bottom = 2.dp),
        color = normalColor
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewRatingTag() {
    RatingTag(
        feedback = Feedback(
            count = 4,
            rating = 4.3f
        )
    )
}