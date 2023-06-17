package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme

@Composable
fun HeaderHome(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String?,
    onClickSetting: () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35F)

    ) {
        Image(
            painter = painterResource(id = R.drawable.curve_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )


                Spacer(modifier = Modifier.weight(1F))

                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onClickSetting() }
                )

            }

            Spacer(modifier = Modifier.weight(1F, true))

            Text(
                text = stringResource(R.string.hello) + name + "!",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                ),
                modifier = Modifier
                    .padding(start = 24.dp)
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.45F))
        }

    }
}


@Preview(
    showSystemUi = true,
    device = "id:Nexus One"
)
@Preview(showSystemUi = true)
@Composable
fun HeaderHomePreview() {
    OTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderHome(
                    modifier = Modifier,
                    imageUrl = "imageUrl",
                    onClickSetting = {},
                    name = "Shahad"
                )
            }
        }
    }
}
