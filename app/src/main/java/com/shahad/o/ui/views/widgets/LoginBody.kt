package com.shahad.o.ui.views.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.circle


@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
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

        OCircle(
            size = 100.dp,
        ) {
            circle(circleColor, 45F)
            circle(smileColor, 45F)
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
            LoginBody(onClickSignUp = {})
        }
    }
}

