package com.shahad.o.ui.views.widgets

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shahad.o.ui.theme.OTheme


@Composable
fun LoginBody(
    modifier: Modifier = Modifier,
) {

}



@Preview(showSystemUi = true, showBackground = true)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    OTheme {
        Surface {
            LoginBody()
        }
    }
}

