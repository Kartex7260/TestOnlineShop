package kanti.testonlineshop.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.textDarkGrey
import kanti.testonlineshop.ui.theme.title4

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IconTextButton(
    modifier: Modifier = Modifier,
    @DrawableRes preIcon: Int,
    @DrawableRes postIcon: Int? = null,
    text: String = "Label",
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit = {}
) = Surface(
    onClick = onClick,
    modifier = modifier,
    color = Color.Transparent
) {
    Row(
        modifier = Modifier
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = preIcon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.textDarkGrey,
            style = MaterialTheme.typography.title4
        )
        if (postIcon != null) {
            Image(
                painter = painterResource(id = postIcon),
                contentDescription = null
            )
        }
    }
}

@Preview()
@Composable
private fun PreviewIconTextButton() {
    IconTextButton(
        preIcon = R.drawable.plus,
        postIcon = R.drawable.plus
    )
}