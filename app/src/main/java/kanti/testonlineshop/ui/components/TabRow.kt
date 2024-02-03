package kanti.testonlineshop.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.backgroundWhite
import kanti.testonlineshop.ui.theme.button2
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title4

@Composable
fun TabRow(
    modifier: Modifier = Modifier,
    items: List<String>,
    selected: Int = 0,
    contentPadding: PaddingValues = PaddingValues(3.dp),
    onSelected: (Int) -> Unit = {}
) = Row(
    modifier = modifier
        .background(
            color = MaterialTheme.colors.backgroundLightGrey,
            shape = RoundedCornerShape(10.dp)
        )
) {
    Row(
        modifier = Modifier.padding(contentPadding)
    ) {
        val weight = rememberSaveable { 1f / items.size }
        items.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier
                    .weight(weight),
                text = item,
                isSelected = selected == index,
                onClick = { onSelected(index) }
            )
            if (index < items.size - 1) {
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    }
}

@Composable
private fun Tab(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) = Box(
    modifier = modifier
        .fillMaxHeight()
        .clip(RoundedCornerShape(8.dp))
        .background(
            shape = RoundedCornerShape(8.dp),
            color = if (isSelected) MaterialTheme.colors.backgroundWhite
            else MaterialTheme.colors.backgroundLightGrey,
        )
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = true),
            enabled = true,
            onClick = onClick,
        ),
    propagateMinConstraints = true
) {
    val enterAnim = remember { fadeIn() }
    val exitAnim = remember { fadeOut() }
    AnimatedVisibility(visible = isSelected, enter = enterAnim, exit = exitAnim) {
        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 0.dp, y = (-1).dp)
                .wrapContentSize(),
            text = text,
            style = MaterialTheme.typography.button2,
            color = MaterialTheme.colors.textBlack
        )
    }
    AnimatedVisibility(visible = !isSelected, enter = enterAnim, exit = exitAnim) {
        Text(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 0.dp, y = (-1).dp)
                .wrapContentSize(),
            text = text,
            style = MaterialTheme.typography.title4,
            color = MaterialTheme.colors.textGrey
        )
    }
}

@Preview
@Composable
fun PreviewTabRow(
    @PreviewParameter(PreviewDoubleTabRowItems::class) items: List<String>
) {
    var selected by remember { mutableIntStateOf(0) }
    TabRow(
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth(),
        items = items,
        selected = selected,
        onSelected = { selected = it }
    )
}

class PreviewDoubleTabRowItems : CollectionPreviewParameterProvider<List<String>>(listOf(
    listOf("First", "Second"),
    listOf("One", "Two", "Three")
))