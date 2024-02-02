package kanti.testonlineshop.ui.components.product

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.components.buttons.TextButton

@Composable
fun CollapsedIngredients(
    modifier: Modifier = Modifier,
    ingredients: String,
    initExpanded: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 2,
    textExpanded: String = "",
    textCollapsed: String = ""
) = Column(
    modifier = modifier
) {
    var expanded by rememberSaveable { mutableStateOf(initExpanded) }
    var textLines by rememberSaveable { mutableIntStateOf(0) }
    var visualOverflow by rememberSaveable { mutableStateOf(false) }
    Text(
        modifier = Modifier
            .animateContentSize(),
        text = ingredients,
        maxLines = if (expanded) maxLines else minLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            textLines = textLayoutResult.lineCount
            if (!visualOverflow) {
                visualOverflow = textLayoutResult.hasVisualOverflow
            }
        }
    )
    if (textLines >= 2 && visualOverflow) {
        TextButton(
            text = if (expanded) textExpanded else textCollapsed,
            onClick = { expanded = !expanded }
        )
    }
}

@Preview
@Composable
fun PreviewCollapsedIngredients() {
    CollapsedIngredients(
        modifier = Modifier.width(100.dp),
        ingredients = "test test test test test test test",
        textExpanded = "Hide",
        textCollapsed = "Show"
    )
}