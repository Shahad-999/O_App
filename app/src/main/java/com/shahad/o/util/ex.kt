package com.shahad.o.util

import android.util.Log

fun Any?.log(tag: String = "O_APP"){
    Log.i(tag,this.toString())
}