package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme

@Composable
fun YearPicker(
    modifier: Modifier,
    currentYear: String,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ){

        IconButton(
            onClick = onClickPrevious) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = OTheme.colors.shade2
            )
        }
        Text(

            currentYear,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
                color = OTheme.colors.shade1,
                textAlign = TextAlign.Center,
            )
        )

        IconButton(
            onClick = onClickNext) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = OTheme.colors.shade2
            )


        }
    }


}