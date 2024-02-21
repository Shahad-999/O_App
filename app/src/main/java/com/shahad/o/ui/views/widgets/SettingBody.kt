package com.shahad.o.ui.views.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shahad.o.R
import com.shahad.o.util.UserData
import kotlinx.coroutines.flow.StateFlow


@Composable
fun SettingBody(
    modifier: Modifier = Modifier,
    userInfo: UserData,
    isDarkTheme: StateFlow<Boolean?>,
    inThemeChange: (Boolean) -> Unit,
    onNotificationsStatusChange: (Boolean) -> Unit,
    isNotificationsOn: StateFlow<Boolean>,
    onClickQuestions: () -> Unit,
    onClickCalendar: () -> Unit,
    onClickStatistics: () -> Unit,
    onClickSignOut: () -> Unit
) {
    val isDark by isDarkTheme.collectAsState()
    val isNotificationsTurnOn by isNotificationsOn.collectAsState()
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        item {
            ProfileSection(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp), user = userInfo)
        }
        item{
            SettingRow(icon = painterResource(id = R.drawable.half_moon), text = "Dark Mode") {
                OSwitch(
                    checked = isDark ?: isSystemInDarkTheme(),
                    onCheckedChange = inThemeChange,
                )
            }
        }
        item{
            SettingRow(
                icon = painterResource(id = R.drawable.question_mark),
                text = "Questions",
                onClick = onClickQuestions
            )
        }
        item{
            SettingRow(
                icon = painterResource(id = R.drawable.notification),
                text = "Notifications"
            ) {
                OSwitch(
                    checked = isNotificationsTurnOn,
                    onCheckedChange = onNotificationsStatusChange,
                )
            }
        }
        item{
            SettingRow(
                icon = painterResource(id = R.drawable.pie_chart),
                text = "Statistic",
                onClick = onClickStatistics


            )
        }
        item{
            SettingRow(
                icon = painterResource(id = R.drawable.calendar),
                text = "Calendar",
                onClick = onClickCalendar
            )
        }
        item{
            SettingRow(
                icon = painterResource(id = R.drawable.sign_out),
                text = "Sign out",
                onClick = onClickSignOut
            )
        }
    }
}

