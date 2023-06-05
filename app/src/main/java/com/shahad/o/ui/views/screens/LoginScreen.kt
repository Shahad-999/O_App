package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.viewModels.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
) {
    LoginScreenBody(
        modifier = modifier
    )

    LaunchedEffect(true) {
    }
}

@Composable
fun LoginScreenBody(
    modifier: Modifier = Modifier,
) {


}

@Preview(showSystemUi = true, showBackground = true)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    OTheme {
        Surface {
            LoginScreenBody()

        }
    }
}

