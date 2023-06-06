package com.shahad.o.ui.views.widgets

import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.viewModels.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
    googleSignIn: (AuthCredential) -> Unit,
    onClickSignUp: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.googleState.value

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val result = account.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(result.idToken, null)
            googleSignIn(credential)
            Log.i("O_APP_TOKEN",result.idToken ?: "NULL")
        } catch (e: ApiException) {
            Log.i("O_APP", e.message.toString())
        }
    }

    val onClickSignIn = {
        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(context.getString(R.string.gcp_id))
            .build()

        val signInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(signInClient.signInIntent)
    }

    Column(
        modifier
            .fillMaxSize()
            .background(OTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val circleColor =
            if (isSystemInDarkTheme()) OTheme.colors.primary else OTheme.colors.primaryVariant
        val smileColor =
            if (isSystemInDarkTheme()) OTheme.colors.primaryVariant else OTheme.colors.primary

        Canvas(modifier = Modifier.size(100.dp)) {
            drawArc(
                color = circleColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = 45F, cap = StrokeCap.Round),
            )
            drawArc(
                color = smileColor,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                style = Stroke(width = 45F, cap = StrokeCap.Round),
            )
        }

        Text(
            text = stringResource(id = R.string.keep_smiling),
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
            style = TextStyle(
                color = OTheme.colors.primary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp
            )
        )

        OButton(
            modifier = Modifier.padding(horizontal = 40.dp),
            text = stringResource(id = R.string.Login),
            onClick = onClickSignUp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlineOButton(
            modifier = Modifier.padding(horizontal = 40.dp),
            text = stringResource(id = R.string.signup),
            onClick = onClickSignIn
        )
    }


    LaunchedEffect(key1 = state) {
        if (state == null) {
            Log.i("O_APP", "state is null")
        } else {

            Log.i("O_APP", "state is ${state.user?.uid}")

        }
    }
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
            LoginBody(googleSignIn = {}, onClickSignUp = {})
        }
    }
}

