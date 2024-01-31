package kanti.testonlineshop.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kanti.testonlineshop.R
import kanti.testonlineshop.ui.components.edittext.EditText
import kanti.testonlineshop.ui.components.edittext.PhoneEditText
import kanti.testonlineshop.ui.components.normalbutton.NormalButton
import kanti.testonlineshop.ui.screens.login.viewmodel.LoginViewModel
import kanti.testonlineshop.ui.theme.caption1
import kanti.testonlineshop.ui.theme.link
import kanti.testonlineshop.ui.theme.textBlack
import kanti.testonlineshop.ui.theme.textGrey
import kanti.testonlineshop.ui.theme.title1

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = LoginViewModel
) {
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
                isError = !name.isValid,
                onValueChanged = { newName -> viewModel.changeName(newName) },
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
                isError = !lastName.isValid,
                onValueChanged = { newLastName -> viewModel.changeLastName(newLastName) },
                placeholder = stringResource(id = R.string.last_name)
            )

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
                enabled = name.text.isNotBlank() && name.isValid &&
                lastName.text.isNotBlank() && lastName.isValid && phone.isValid
            ) { viewModel.login() }
        }

        // FOOTER
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Нажимая кнопку “Войти”, Вы принимаете",
                style = MaterialTheme.typography.caption1,
                color = MaterialTheme.colors.textGrey
            )
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "условия программы лояльности",
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