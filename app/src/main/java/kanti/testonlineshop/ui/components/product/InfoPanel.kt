package kanti.testonlineshop.ui.components.product

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kanti.testonlineshop.data.model.Info
import kanti.testonlineshop.ui.components.card.SpecificationCard

@Composable
fun InfoPanel(
    modifier: Modifier = Modifier,
    items: List<Info>
) = Column(
    modifier = modifier
) {
    for (info in items) {
        SpecificationCard(info = info)
    }
}

@Preview
@Composable
fun PreviewInfoPanel() {
    InfoPanel(
        items = listOf(
            Info("Article", "2305-95"),
            Info("Test 1", "testing"),
            Info("Test 2", "Testable")
        )
    )
}