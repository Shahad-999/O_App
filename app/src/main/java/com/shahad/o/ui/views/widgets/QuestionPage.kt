package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.shahad.o.ui.theme.OTheme

@Composable
fun QuestionPage(
    modifier: Modifier = Modifier,
    question: String,
    imageUrl: String,
    onClickYes: () -> Unit,
    onClickNo: () -> Unit,
    isYesPositive: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                OTheme.colors.primary,
                RoundedCornerShape(36.dp)
            )
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.12F))
        Text(
            text = question,
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.weight(0.09F))
        AsyncImage(
            model = imageUrl,
            contentDescription = "Profile",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35F)
        )

        Spacer(modifier = Modifier.weight(0.09F))
        RecordButtons(
            modifier = Modifier.fillMaxWidth(),
            onClickYes = onClickYes,
            onClickNo = onClickNo,
            isYesPositive = isYesPositive
        )
        Spacer(modifier = Modifier.weight(0.11F))
    }
}


@Preview(showBackground = true)
@Composable
fun QuestionPagePreview() {
    OTheme {
        Surface {
            QuestionPage(
                question = "Are you happy? ",
                imageUrl = "",
                onClickYes = {},
                onClickNo = {},
                isYesPositive = false
            )
        }
    }
}
