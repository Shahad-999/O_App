package com.shahad.o.ui.views.widgets


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahad.o.ui.theme.OTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationAppBar(
    modifier: Modifier = Modifier,
    backToHome: () -> Unit
) {
    TopAppBar(
        modifier = modifier.padding(horizontal = 24.dp),
        title = {
            Text(
                text = "Setting",
                modifier = Modifier.padding(start = 8.dp),
                style = TextStyle(
                    color = OTheme.colors.shade1,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { backToHome() },
                tint = OTheme.colors.shade1
            )
        },
    )
}


@Preview(showBackground = true)
@Composable
fun NavigationAppBarPreview() {
    OTheme {
        Surface {
            NavigationAppBar(backToHome = {})
        }
    }
}
