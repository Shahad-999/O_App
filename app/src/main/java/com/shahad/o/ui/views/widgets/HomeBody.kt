package com.shahad.o.ui.views.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.circle


@Composable
fun HomeBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val primaryColor = OTheme.colors.primary
        HeaderHome(modifier = Modifier)

        Box(
            modifier = Modifier
                .fillMaxHeight(1.5F)
                .padding(bottom = 80.dp)
                .size(125.dp),
            contentAlignment = Alignment.Center
        ) {
            OCircle(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { Log.i("O_APP", "HI ") },
                size = 125.dp
            ) {
                circle(color = primaryColor, 30F)
            }
            Text(
                text = "START",
                style = TextStyle(
                    color = OTheme.colors.primary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
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
            HomeBody()
        }
    }
}

