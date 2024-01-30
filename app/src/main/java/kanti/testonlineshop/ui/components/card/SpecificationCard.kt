package kanti.testonlineshop.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.data.model.Info
import kanti.testonlineshop.ui.theme.elementLightGrey
import kanti.testonlineshop.ui.theme.text1
import kanti.testonlineshop.ui.theme.textDarkGrey

@Composable
fun SpecificationCard(
    modifier: Modifier = Modifier,
    info: Info
) = Column(modifier = modifier) {
    Spacer(modifier = Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = info.title,
            style = MaterialTheme.typography.text1,
            color = MaterialTheme.colors.textDarkGrey
        )
        Text(
            text = info.value,
            style = MaterialTheme.typography.text1,
            color = MaterialTheme.colors.textDarkGrey
        )
    }
    Spacer(modifier = Modifier.height(3.dp))
    Divider(
        color = MaterialTheme.colors.elementLightGrey
    )
}

@Preview
@Composable
fun PreviewSpecificationCard() {
    SpecificationCard(
        info = Info(
            title = "Title",
            value = "Value"
        )
    )
}