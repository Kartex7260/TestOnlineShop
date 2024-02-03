package kanti.testonlineshop.ui.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kanti.testonlineshop.R
import kanti.testonlineshop.data.model.login.LoginResult
import kanti.testonlineshop.ui.components.edittext.EditText
import kanti.testonlineshop.ui.components.edittext.PhoneEditText
import kanti.testonlineshop.ui.components.normalbutton.NormalButton
import kanti.testonlineshop.ui.screens.login.viewmodel.LoginViewModel
import kanti.testonlineshop.ui.screens.login.viewmodel.LoginViewModelImpl
import kanti.testonlineshop.ui.theme.caption1
import kanti.testonlineshop.ui.theme.link
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title1
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    context: Context = LocalContext.current,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModelImpl>()
) {
    fun navigate(destination: String) {
        navController.navigate(
            route = "${context.getString(R.string.nav_main)}/$destination"
        ) {
            popUpTo(
                route = context.getString(R.string.nav_login)
            ) {
                inclusive = true
            }
        }
    }
    LaunchedEffect(key1 = viewModel) {
        viewModel.loginResult.collectLatest { result ->
            when (result) {
                is LoginResult.Register -> {
                    navigate(context.getString(R.string.nav_main_main))
                }

                is LoginResult.Success -> {
                    navigate(context.getString(R.string.nav_main_catalog))
                }

                is LoginResult.InvalidCredentials -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.invalid_login_info),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    Column(
        modifier = modifier
    ) {
        // TITLE BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.title1,
                color = MaterialTheme.colors.textBlack
            )
        }

        // CONTENT
        Spacer(modifier = Modifier.height(113.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            val name by viewModel.name.collectAsState()
            EditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    ),
                value = name.text,
                isError = !name.valid,
                onValueChanged = { newName -> viewModel.changeName(newName) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                placeholder = stringResource(id = R.string.name)
            )

            val lastName by viewModel.lastName.collectAsState()
            EditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    ),
                value = lastName.text,
                isError = !lastName.valid,
                onValueChanged = { newLastName -> viewModel.changeLastName(newLastName) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                placeholder = stringResource(id = R.string.last_name)
            )

            val loginIsAllow by viewModel.loginButtonEnabled.collectAsState()
            val phone by viewModel.phone.collectAsState()
            PhoneEditText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    ),
                value = phone.text,
                onValueChanged = { newPhone -> viewModel.changePhone(newPhone) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = if (loginIsAllow) ImeAction.Done
                    else ImeAction.None
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (loginIsAllow) {
                            viewModel.login()
                            defaultKeyboardAction(ImeAction.Done)
                        } else {
                            defaultKeyboardAction(ImeAction.None)
                        }
                    }
                ),
                placeholder = stringResource(id = R.string.phone_number)
            )

            NormalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 32.dp
                    ),
                text = stringResource(id = R.string.logon),
                enabled = loginIsAllow
            ) { viewModel.login() }
        }

        // FOOTER
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_credits_1),
                style = MaterialTheme.typography.caption1,
                color = MaterialTheme.colors.textGrey
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = stringResource(id = R.string.login_credits_2),
                style = MaterialTheme.typography.link,
                color = MaterialTheme.colors.textGrey
            )
            Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        viewModel = LoginViewModel
    )
}