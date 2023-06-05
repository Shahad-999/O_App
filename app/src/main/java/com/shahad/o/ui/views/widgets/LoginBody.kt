package com.shahad.o.ui.views.widgets

import android.content.res.Configuration
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme


@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
    onClickLogin: () -> Unit,
    onClickSignUp: () -> Unit,
) {
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
            onClick = onClickLogin
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlineOButton(
            modifier = Modifier.padding(horizontal = 40.dp),
            text = stringResource(id = R.string.signup),
            onClick = onClickSignUp
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
fun LoginScreenPreview() {
    OTheme {
        Surface {
            LoginBody(onClickLogin = {}, onClickSignUp = {})
        }
    }
}

