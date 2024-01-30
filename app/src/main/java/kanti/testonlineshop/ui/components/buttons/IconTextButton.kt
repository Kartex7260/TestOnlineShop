package kanti.testonlineshop.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.textDarkGrey
import kanti.testonlineshop.ui.theme.title4

@Composable
fun IconTextButton(
    modifier: Modifier = Modifier,
    @DrawableRes preIcon: Int,
    @DrawableRes postIcon: Int,
    text: String = "Label",
    onClick: () -> Unit = {}
) = TextButton(
    onClick = onClick,
    modifier = modifier
        .defaultMinSize(24.dp, 24.dp),
    contentPadding = PaddingValues(0.dp)
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
    Image(
        painter = painterResource(id = postIcon),
        contentDescription = null
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewIconTextButton() {
    IconTextButton(
        preIcon = R.drawable.plus,
        postIcon = R.drawable.plus
    )
}