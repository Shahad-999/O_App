package com.shahad.o.util

import android.util.Log

fun <T> T?.log(tag: String = "O_APP"): T? {
    Log.i(tag, this.toString())
    return this
}