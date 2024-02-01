package kanti.testonlineshop.ui.screens.main.catalog

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.screens.main.catalog.viewmodel.SortType
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.title2

@Composable
fun SortMenu(
    expandMenuState: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    setSort: (SortType) -> Unit = {}
) = DropdownMenu(
    expanded = expandMenuState.value,
    onDismissRequest = { expandMenuState.value = false },
    offset = DpOffset(x = 8.dp, y = 0.dp)
) {
    DropdownMenuItem(
        onClick = {
            setSort(SortType.Rating)
            expandMenuState.value = false
        }
    ) {
        Text(
            text = stringResource(id = R.string.sort_rating),
            style = MaterialTheme.typography.title2,
            color = MaterialTheme.colors.textBlack
        )
    }

    DropdownMenuItem(
        onClick = {
            setSort(SortType.PriceReduction)
            expandMenuState.value = false
        }
    ) {
        Text(
            text = stringResource(id = R.string.sort_price_reduction),
            style = MaterialTheme.typography.title2,
            color = MaterialTheme.colors.textBlack
        )
    }

    DropdownMenuItem(
        onClick = {
            setSort(SortType.PriceIncrease)
            expandMenuState.value = false
        }
    ) {
        Text(
            text = stringResource(id = R.string.sort_price_increase),
            style = MaterialTheme.typography.title2,
            color = MaterialTheme.colors.textBlack
        )
    }
}

@Preview
@Composable
fun PreviewSortMenu() {
    val expandMenu = remember { mutableStateOf(true) }
    SortMenu(
        expandMenuState = expandMenu
    )
}