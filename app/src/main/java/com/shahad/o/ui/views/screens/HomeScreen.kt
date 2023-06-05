package com.shahad.o.ui.views.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shahad.o.ui.theme.OTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    HomeScreenBody(
        modifier = modifier
    )

}

@Composable
fun HomeScreenBody(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home Screen")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun HomeScreenPreview() {
    OTheme {
        Surface {
            HomeScreenBody()

        }
    }
}

