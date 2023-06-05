package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme

@Composable
fun OButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OTheme.colors.primary,
            contentColor = Color.White
        ),

        shape = RoundedCornerShape(8.dp)
    ) {

        Text(
            text,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
fun OutlineOButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = OTheme.colors.primary
        ),

        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(4.dp,OTheme.colors.primary)
    ) {
        Text(
            text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}


@Preview
@Composable
fun OButtonPreview() {
    OTheme {
        Surface {
            OButton(
                text = "Login With Google"
            ) {

            }
        }
    }
}


@Preview
@Composable
fun OutlineOButtonPreview() {
    OTheme {
        Surface {
            OutlineOButton(
                text = "Login With Google"
            ) {

            }
        }
    }
}

