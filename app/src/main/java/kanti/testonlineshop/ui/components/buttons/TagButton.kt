package kanti.testonlineshop.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.elementDarkBlue
import kanti.testonlineshop.ui.theme.elementWhite
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.textWhite
import kanti.testonlineshop.ui.theme.title4

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagButton(
    modifier: Modifier = Modifier,
    select: Boolean = true,
    text: String = "Label",
    @DrawableRes tailIcon: Int,
    onClick: () -> Unit = {},
    iconClick: () -> Unit = {}
) = Surface(
    shape = RoundedCornerShape(100.dp),
    color = if (select) MaterialTheme.colors.elementDarkBlue
    else MaterialTheme.colors.backgroundLightGrey,

    modifier = modifier
        .semantics { role = Role.Button }
        .height(28.dp),
    onClick = onClick
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            modifier = Modifier.offset(y = (-1).dp),
            text = text,
            color = if (select) MaterialTheme.colors.textWhite
            else MaterialTheme.colors.textGrey,
            style = MaterialTheme.typography.title4
        )
        Spacer(modifier = Modifier.width(4.dp))
        if (select) {
            IconButton(
                modifier = Modifier.size(20.dp),
                iconId = tailIcon,
                tint = MaterialTheme.colors.elementWhite
            ) { iconClick() }
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Preview
@Composable
fun PreviewTagButton(
    @PreviewParameter(PreviewChipButtonSelect::class) select: Boolean
) {
    TagButton(
        tailIcon = R.drawable.tag_clear,
        select = select
    )
}

class PreviewChipButtonSelect : CollectionPreviewParameterProvider<Boolean>(listOf(true, false))