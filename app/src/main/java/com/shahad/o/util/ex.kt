package com.shahad.o.util

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
