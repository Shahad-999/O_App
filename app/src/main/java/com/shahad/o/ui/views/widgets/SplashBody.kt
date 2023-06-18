package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.ui.theme.OTheme


@Composable
fun SplashBody(
    modifier: Modifier = Modifier,
    onCompleteFlipping: ()-> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Eyes(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.19F),
            onCompleteFlipping = onCompleteFlipping
        )
        Smile(
            modifier = Modifier
                .fillMaxWidth(0.38F)
                .aspectRatio(1F)
        )
    }

}


@Preview(showSystemUi = true, showBackground = true)
//@Preview(
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//    showBackground = true
//)
@Composable
fun SplashScreenPreview() {
    OTheme {
        Surface {
            SplashBody(onCompleteFlipping = {})
        }
    }
}

