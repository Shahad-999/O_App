package com.shahad.o.ui.views.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.ui.util.gradiantCircle

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

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .padding(top = 32.dp)
            .size(125.dp),
        contentAlignment = Alignment.Center
    ) {

        val colors = OTheme.colors.gradiantColors
        val infiniteTransition = rememberInfiniteTransition()
        val rotateAnimation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        )

        OCircle(
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onClick() },
            size = 125.dp,
        ) {
            gradiantCircle(
                colors = colors,
                rotateAnimation = rotateAnimation,
                strokeWidth = 30F
            )
        }
        Text(
            text = stringResource(R.string.start),
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



@Preview
@Composable
fun StartButtonPreview() {
    OTheme {
        Surface {
            StartButton{
            }
        }
    }
}

