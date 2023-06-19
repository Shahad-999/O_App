package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shahad.o.R
import com.shahad.o.ui.theme.OTheme
import com.shahad.o.util.UserData


@Composable
fun SettingBody(
    modifier: Modifier = Modifier,
    userInfo: UserData
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        ProfileSection(modifier = Modifier.padding(vertical = 8.dp), user = userInfo)
        SettingRow(icon = painterResource(id = R.drawable.half_moon), text = "Dark Mode") {
            OSwitch(
                checked = true,
                onCheckedChange = {},
            )
        }

        Divider()
        SettingRow(
            icon = painterResource(id = R.drawable.question_mark),
            text = "Questions"
        )
        Divider()
        SettingRow(
            icon = painterResource(id = R.drawable.notification),
            text = "Notifications"
        ) {
            OSwitch(
                checked = true,
                onCheckedChange = {},
            )
        }
        Divider()
        SettingRow(
            icon = painterResource(id = R.drawable.pie_chart),
            text = "Statistic"
        )
        Divider()
        SettingRow(
            icon = painterResource(id = R.drawable.calendar),
            text = "Calendar"
        )
        Divider()
        SettingRow(
            icon = painterResource(id = R.drawable.sign_out),
            text = "Sign out"
        )
        Divider()
    }
}


@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    OTheme {
        Surface {
            SettingBody(userInfo = UserData.defaultUser)
        }
    }
}

