package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.shahad.o.ui.theme.OTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecordButtons(
    modifier: Modifier = Modifier,
    onClickYes: () -> Unit,
    onClickNo: () -> Unit
){

    Row(modifier = modifier) {
        RecordButton(text = "YES", modifier =  Modifier.weight(1F), onClick = onClickYes)
        Spacer(modifier = Modifier.width(24.dp))
        RecordButton(text = "NO", modifier =  Modifier.weight(1F), onClick = onClickNo, backgroundColor = Color(0x94FFFFFF))
    }
}

@Composable
fun RecordButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Color.White,
    onClick: () -> Unit
){
        Box(
            modifier = modifier
                .height(56.dp)
                .background(backgroundColor, RoundedCornerShape(8.dp))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                style = TextStyle(
                    color = OTheme.colors.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }
}


@Preview(showBackground = true)
@Composable
fun RecordButtonsPreview() {
    OTheme {
        Surface {
            RecordButtons( onClickYes = {}, onClickNo = {})
        }
    }
}
