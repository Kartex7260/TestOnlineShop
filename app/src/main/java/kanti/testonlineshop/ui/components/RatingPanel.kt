package kanti.testonlineshop.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.Feedback
import kanti.testonlineshop.ui.theme.elementOrange
import kanti.testonlineshop.ui.theme.text1
import kanti.testonlineshop.ui.theme.textGrey

@Composable
fun RatingPanel(
    modifier: Modifier = Modifier,
    feedback: Feedback
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.height(20.dp)
    ) {
        Star(
            current = feedback.rating,
            min = 0f,
            max = 1f
        )
        Star(
            current = feedback.rating,
            min = 1f,
            max = 2f
        )
        Star(
            current = feedback.rating,
            min = 2f,
            max = 3f
        )
        Star(
            current = feedback.rating,
            min = 3f,
            max = 4f
        )
        Star(
            current = feedback.rating,
            min = 4f,
            max = 5f
        )
    }
    Spacer(modifier = Modifier.width(8.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color = MaterialTheme.colors.textGrey
        val textStyle = MaterialTheme.typography.text1
        Text(
            modifier = Modifier.offset(x = 0.dp, y = (-1).dp),
            text = feedback.rating.toString(),
            style = textStyle,
            color = color
        )

        val radius = with(LocalDensity.current) { 1.dp.toPx() }
        Canvas(
            modifier = Modifier.size(width = 14.dp, height = 16.dp)
        ) {
            drawCircle(color, radius)
        }

        Text(
            modifier = Modifier.offset(x = 0.dp, y = (-1).dp),
            text = pluralStringResource(
                id = R.plurals.product_feedback_count,
                count = feedback.count,
                formatArgs = arrayOf(feedback.count)
            ),
            style = textStyle,
            color = color
        )
    }
}

@Composable
private fun Star(
    modifier: Modifier = Modifier,
    current: Float = 0f,
    min: Float = 0f,
    max: Float = 1f
) {
    val half = (min + max) / 2
    val painterRes = when(current) {
        in Float.MIN_VALUE..<min -> R.drawable.star_empty
        in min..<half -> R.drawable.star_empty
        in half..<max -> R.drawable.star_half
        in max..Float.MAX_VALUE -> R.drawable.star_full
        else -> R.drawable.star_empty
    }

    Image(
        modifier = modifier,
        painter = painterResource(id = painterRes),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.elementOrange)
    )
}

@Preview
@Composable
fun PreviewRatingPanel() {
    RatingPanel(feedback = Feedback(100, 3.5f))
}