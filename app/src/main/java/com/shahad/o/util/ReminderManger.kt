package com.shahad.o.util

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar
import java.util.Locale

class ReminderManger(
    private val context: Context
) {

    init {
        startReminder()
    }
    @SuppressLint("ScheduleExactAlarm")
    fun startReminder(
        reminderId: Int = REMINDER_REQUEST_CODE
    ){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val (hours, min) = listOf(0,0)
        val intent = Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->

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



    companion object{
        private const val REMINDER_REQUEST_CODE = 101
    }
}