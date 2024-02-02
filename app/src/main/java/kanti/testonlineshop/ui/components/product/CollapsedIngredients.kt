package kanti.testonlineshop.ui.components.product

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
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
    Text(
        modifier = Modifier
            .animateContentSize(),
        text = ingredients,
        maxLines = if (expanded) maxLines else minLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            textLines = textLayoutResult.lineCount
        }
    )
    if (textLines >= 2) {
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
        ingredients = "fjfij bejfij  joj  j fjo wef efweew gwg wr gr  grgsklgsrkgj ksfk sklrf k kw" +
                "knf  jkjefkef kj jsfosjefjs kjlsd kfjksmk ksdk ksnfksnk kngklnl eknlkng f;e;f ;kef " +
                "fnsjnf lsnf eklfkj slkjflksfkj slkfj lskj flksejf lsjf slegl s gl srrglg sr",
        textExpanded = "Hide",
        textCollapsed = "Show"
    )
}