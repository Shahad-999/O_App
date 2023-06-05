package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.viewModels.SplashViewModel
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel(),
    navToHome: () -> Unit,
    navToLogin: () -> Unit,
) {
    SplashScreenBody(
        modifier = modifier
    )
    LaunchedEffect(true) {
        viewModel.token.collect { token ->
            if (token == null) {
                navToLogin()
            } else {
                Log.i("TOKEN", token)
                navToHome()
            }

        }

    }
}

@Composable
fun SplashScreenBody(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            alignment = Alignment.Center
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun SplashScreenPreview() {
    OTheme {
        Surface {
            SplashScreenBody()

        }
    }
}

