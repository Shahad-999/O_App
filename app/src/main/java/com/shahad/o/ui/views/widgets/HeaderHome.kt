package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme

@Composable
fun HeaderHome(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35F)
            .background(
                OTheme.colors.primary,
                RoundedCornerShape(bottomEnd = 48.dp, bottomStart = 48.dp)
            )
    ) {

        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.weight(1F))

            Image(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )

        }
        Spacer(modifier = Modifier.weight(1F))

        Text(
            "Have a great day!",
            style = TextStyle(
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                letterSpacing = 10.sp,
            ),
            modifier = Modifier
                .padding(start = 24.dp,end = 40.dp,bottom = 40.dp)
        )

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
                HeaderHome(modifier = Modifier)
            }
        }
    }
}
