package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.ui.theme.OTheme

@Composable
fun QuestionPage(
    modifier: Modifier = Modifier,
    question: String,
    imageUrl: String,
    onClickYes: () -> Unit,
    onClickNo: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                OTheme.colors.primary,
                RoundedCornerShape(36.dp)
            )
    ){

    }
}


@Preview(showBackground = true)
@Composable
fun QuestionPagePreview() {
    OTheme {
        Surface {
            QuestionPage( question = "", imageUrl = "", onClickYes = {}, onClickNo = {})
        }
    }
}
