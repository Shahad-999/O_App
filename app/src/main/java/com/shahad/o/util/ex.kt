package com.shahad.o.util

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shahad.o.util.Constant.THIRTY_DAYS
import com.shahad.o.util.Constant.THIRTY_ONE_DAYS
import com.shahad.o.util.Constant.TWENTY_DAYS
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.koin.java.KoinJavaComponent.inject

fun <T> T?.log(tag: String = "O_APP"): T? {
    Log.i(tag, this.toString())
    return this
}

fun <T> T.serializeToMap(): Map<String, Any> {
    return convert()
}

inline fun <reified T> Map<String, Any>.toDataClass(): T {
    return convert()
}

inline fun <I, reified O> I.convert(): O {
    val gson : Gson by inject(Gson::class.java)
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}

fun Int.getDays(): List<Int>{
    return if(this == 3 || this == 5 || this == 8 || this == 10) THIRTY_DAYS else if (this==1) TWENTY_DAYS else THIRTY_ONE_DAYS
}
fun Int.toMonth(): String {
    return when (this) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid Month"
    }
}

fun Instant.atStartOfDay(timeZone: TimeZone = TimeZone.currentSystemDefault()): Instant {
    this.toLocalDateTime(timeZone).apply {
        return LocalDateTime(year, monthNumber,dayOfMonth,0,0,0).toInstant(TimeZone.UTC)
    }
}

fun Double.toEmoji(): String {
    return when {
        this >= 0.9 -> "😁"
        this >= 0.8 -> "😄"
        this >= 0.7 -> "😃"
        this >= 0.6 -> "😊"
        this >= 0.5 -> "😐"
        this >= 0.4 -> "😕"
        this >= 0.3 -> "😟"
        this >= 0.2 -> "😢"
        this >= 0.1 -> "😭"
        else -> "😖"
    }
}

 fun Boolean.toPositiveAnswer(): String {
    return  if(this) "Yes" else "No"
}
fun ClosedRange<Instant>.toEpochMillisecondsRange(): ClosedRange<Long> {
    return (this.start
        .toEpochMilliseconds()..this.endInclusive
        .toEpochMilliseconds())
}

@RequiresApi(Build.VERSION_CODES.O)
fun Int.toDate(): kotlinx.datetime.LocalDate {
   return kotlinx.datetime.LocalDate.fromEpochDays(this)
}
@RequiresApi(Build.VERSION_CODES.O)
fun kotlinx.datetime.LocalDate.toDDMMMFormat(): String {
    return "${this.dayOfMonth}/${this.month.name.take(3)}"
}


fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, length).show()
}
