package com.shahad.o.util

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat
import com.shahad.o.R
import java.util.Calendar
import java.util.Locale

class ReminderManger(
    private val context: Context
) {

    @SuppressLint("ScheduleExactAlarm")
    fun startReminder(
        reminderTime: String = "08:00",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val (hours, min) = reminderTime.split(":").map { it.toInt() }
        val intent =
            Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->

                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderId,
                    intent,
                    PendingIntent.FLAG_MUTABLE
                )
            }

        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, min)
        }
        if(isTimeInPast(calendar)){
            calendar.add(Calendar.DATE,1)
        }

        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis,intent),
            intent
        )
    }

    private fun isTimeInPast(calendar: Calendar) =
        Calendar.getInstance(Locale.ENGLISH)
            .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0


    @SuppressLint("UnspecifiedImmutableFlag")
    fun stopReminder(
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                PendingIntent.FLAG_MUTABLE
            )
        }
        alarmManager.cancel(intent)
    }

    fun createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                context.getString(R.string.reminders_notification_channel_id),
                context.getString(R.string.reminders_notification_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            ContextCompat.getSystemService(context, NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }
    fun startAllNotifications(){
        "start Notifications".log()
        startReminder(
            reminderTime = "6:00",
            reminderId = MORNING_REMINDER_NOTIFICATION_REQUEST_CODE
        )
        startReminder(
            reminderTime = "22:00",
            reminderId = EVENING_REMINDER_NOTIFICATION_REQUEST_CODE
        )
        startReminder(
            reminderTime = "20:30",
        )
    }

    fun stopAllNotifications(){
        "stop Notifications".log()
        stopReminder(
            reminderId = MORNING_REMINDER_NOTIFICATION_REQUEST_CODE
        )
        stopReminder(
            reminderId = EVENING_REMINDER_NOTIFICATION_REQUEST_CODE
        )
        stopReminder()
    }

    companion object{
        const val MORNING_REMINDER_NOTIFICATION_REQUEST_CODE = 123
        const val EVENING_REMINDER_NOTIFICATION_REQUEST_CODE = 100
        private const val REMINDER_NOTIFICATION_REQUEST_CODE = 101
    }
}