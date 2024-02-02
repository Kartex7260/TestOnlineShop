package kanti.testonlineshop.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.ui.components.buttons.TextButton

@Composable
fun CollapsedPanel(
    modifier: Modifier = Modifier,
    initExpandex: Boolean = true,
    textExpanded: String = "",
    textCollapsed: String = "",
    content: @Composable ColumnScope.() -> Unit = {}
) = Column(
    modifier = modifier
) {
    var expanded by rememberSaveable { mutableStateOf(initExpandex) }
    AnimatedVisibility(visible = expanded) {
        Column {
            content()
        }
    }
    TextButton(
        text = if (expanded) textExpanded else textCollapsed,
        onClick = { expanded = !expanded }
    )
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