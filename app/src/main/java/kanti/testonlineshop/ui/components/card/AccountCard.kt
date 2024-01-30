package kanti.testonlineshop.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.caption1
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    tailingIcon: (@Composable () -> Unit)? = null,
    title: String = "",
    subtitle: String? = null,
    onClick: () -> Unit = {}
) = Surface(
    modifier = modifier
        .height(48.dp),
    color = MaterialTheme.colors.backgroundLightGrey,
    shape = RoundedCornerShape(8.dp),
    onClick = onClick
) {
    Row(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(modifier = Modifier.width(16.dp))
            }
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.title2,
                    color = MaterialTheme.colors.textBlack,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (subtitle != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.caption1,
                        color = MaterialTheme.colors.textGrey,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        if (tailingIcon != null) {
            tailingIcon()
        }
    }
}

@Preview
@Composable
private fun PreviewAccountCard() {
    AccountCard(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Image(painter = painterResource(id = R.drawable.plus), contentDescription = null)
        },
        title = "Test",
        subtitle = "Test",
        tailingIcon = {
            Image(painter = painterResource(id = R.drawable.plus), contentDescription = null)
        }
    )
}