package kanti.testonlineshop.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
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
) = Surface(
    shape = RoundedCornerShape(100.dp),
    color = if (select) MaterialTheme.colors.elementDarkBlue
    else MaterialTheme.colors.backgroundLightGrey,

    modifier = modifier
        .height(28.dp)
        .semantics { role = Role.Button },
    onClick = onClick
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
//            top = 2.dp,
            bottom = 2.dp,
            start = 12.dp,
            end = 8.dp
        )
    ) {
        Text(
            text = text,
            color = if (select) MaterialTheme.colors.textWhite
            else MaterialTheme.colors.textGrey,
            style = MaterialTheme.typography.title4
        )
        Spacer(modifier = Modifier.width(4.dp))
        if (select) {
            Image(
                painter = painterResource(id = tailIcon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.elementWhite)
            )
        }
    }
}

@Preview
@Composable
fun PreviewTagButton(
    @PreviewParameter(PreviewChipButtonSelect::class) select: Boolean
) {
    TagButton(
        tailIcon = R.drawable.plus,
        select = select
    )
}

class PreviewChipButtonSelect : CollectionPreviewParameterProvider<Boolean>(listOf(true, false))