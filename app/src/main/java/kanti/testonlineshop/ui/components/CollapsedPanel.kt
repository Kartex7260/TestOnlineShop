package kanti.testonlineshop.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.theme.button1
import kanti.testonlineshop.ui.theme.textGrey

@Composable
fun CollapsedPanel(
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    textExpanded: String = "",
    textCollapsed: String = "",
    content: @Composable () -> Unit = {}
) = Column(
    modifier = modifier
) {
    var expandState by rememberSaveable { mutableStateOf(expanded) }
    AnimatedVisibility(visible = expandState) {
        content()
    }
    Surface(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                enabled = true,
                onClick = { expandState = !expandState }
            ),
        color = Color.Transparent
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    end = 10.dp,
                    bottom = 10.dp
                ),
            text = if (expandState) textExpanded else textCollapsed,
            style = MaterialTheme.typography.button1,
            color = MaterialTheme.colors.textGrey
        )
    }
}

@Preview
@Composable
fun PreviewCollapsedPanel() {
    CollapsedPanel(
        textCollapsed = "Hide",
        textExpanded = "Show"
    ) {
        Box(
            modifier = Modifier
                .size(400.dp)
                .background(color = Color.Red)
        )
    }
}