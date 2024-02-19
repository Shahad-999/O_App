package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme


@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String?,
    isUploadToday: Boolean,
    onClickSetting: () -> Unit,
    onClickStart: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val primaryColor = OTheme.colors.primary
        HeaderHome(
            modifier = Modifier,
            imageUrl = imageUrl,
            onClickSetting = onClickSetting,
            name = name
        )

        Text(
            text = if (isUploadToday) stringResource(R.string.you_already_upload) else stringResource(
                R.string.tell_me_about_your_day
            ),
            style = TextStyle(
                color = primaryColor,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth(0.7f)
        )
        StartButton(
            modifier = Modifier,
            onClick = onClickStart,
            text = if (isUploadToday) stringResource(R.string.update) else stringResource(R.string.start)
        )
    }
}


@Preview(
    showBackground = true,
    device = "id:Nexus One"
)
@Preview(showSystemUi = true, showBackground = true)
//@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL, showBackground = true)
@Composable
fun HomeScreenPreview() {
    OTheme {
        Surface {
            HomeBody(
                imageUrl = "",
                onClickSetting = { },
                isUploadToday = false,
                onClickStart = { },
                name = "Shahad"
            )
        }
    }
}

