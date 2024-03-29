package kanti.testonlineshop.ui.screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.login.User
import kanti.testonlineshop.ui.components.buttons.IconButton
import kanti.testonlineshop.ui.components.card.AccountCard
import kanti.testonlineshop.ui.components.panelbutton.PanelButton
import kanti.testonlineshop.ui.components.panelbutton.PanelButtonDefaults
import kanti.testonlineshop.ui.screens.main.profile.viewmodel.ProfileViewModel
import kanti.testonlineshop.ui.screens.main.profile.viewmodel.ProfileViewModelImpl
import kanti.testonlineshop.ui.theme.backgroundLightGrey
import kanti.testonlineshop.ui.theme.elementBlack
import kanti.testonlineshop.ui.theme.elementDarkGrey
import kanti.testonlineshop.ui.theme.elementOrange
import kanti.testonlineshop.ui.theme.elementPink
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title1
import kotlinx.coroutines.flow.collectLatest

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier
        .height(46.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        modifier = Modifier
            .offset(x = 0.dp, y = (-2).dp),
        text = stringResource(id = R.string.profile_personal_account),
        style = MaterialTheme.typography.title1,
        color = MaterialTheme.colors.textBlack
    )
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModelImpl>(),
    toFavourite: () -> Unit = {},
    logout: () -> Unit = {}
) = Column(
    modifier = modifier
) {
    LifecycleResumeEffect {
        viewModel.updateData()
        onPauseOrDispose {  }
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.onLogout.collectLatest { logout() }
    }

    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            val buttonModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

            val user by viewModel.user.collectAsState()
            AccountButton(
                modifier = buttonModifier,
                user = user
            )
            Spacer(modifier = Modifier.height(24.dp))

            val favouritesCount by viewModel.favouritesCount.collectAsState()
            NavigateButton(
                modifier = buttonModifier,
                title = stringResource(id = R.string.profile_favourites),
                subtitle = if (favouritesCount == 0) null
                else pluralStringResource(
                    id = R.plurals.profile_favourite_count,
                    count = favouritesCount,
                    formatArgs = arrayOf(favouritesCount)
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.heart_empty),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.elementPink)
                    )
                },
                onClick = toFavourite
            )
            Spacer(modifier = Modifier.height(8.dp))

            NavigateButton(
                modifier = buttonModifier,
                title = stringResource(id = R.string.profile_shops),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.shop),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.elementPink)
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            NavigateButton(
                modifier = buttonModifier,
                title = stringResource(id = R.string.profile_feedback),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.feedback),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.elementOrange)
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            NavigateButton(
                modifier = buttonModifier,
                title = stringResource(id = R.string.profile_offer),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.offer),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.textGrey)
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            NavigateButton(
                modifier = buttonModifier,
                title = stringResource(id = R.string.profile_return_product),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.refund),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.textGrey)
                    )
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        PanelButton(
            modifier = Modifier
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter)
                .height(51.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.profile_logout),
            colors = PanelButtonDefaults.colors(
                backgroundColor = MaterialTheme.colors.backgroundLightGrey
            ),
            onClick = { viewModel.logout() }
        )
    }
}

@Composable
private fun AccountButton(
    modifier: Modifier = Modifier,
    user: User,
    iconColor: Color = MaterialTheme.colors.elementDarkGrey,
    onClick: () -> Unit = {}
) = AccountCard(
    modifier = modifier,
    title = "${user.name} ${user.lastName}",
    subtitle = run {
        val regex = """(\d{3})(\d{3})(\d{2})(\d{2})""".toRegex()
        regex.replace(user.phone, "+7 $1 $2-$3-$4")
    },
    leadingIcon = {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = iconColor)
        )
    },
    tailingIcon = {
        IconButton(
            iconId = R.drawable.logout,
            tint = iconColor
        )
    },
    onClick = onClick
)

@Composable
private fun NavigateButton(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {}
) = AccountCard(
    modifier = modifier,
    title = title,
    subtitle = subtitle,
    leadingIcon = leadingIcon,
    tailingIcon = {
        Image(
            painter = painterResource(id = R.drawable.right_arrow),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.elementBlack)
        )
    },
    onClick = onClick
)

@Preview
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(
        viewModel = ProfileViewModel
    )
}