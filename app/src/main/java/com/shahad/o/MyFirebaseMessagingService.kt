package com.shahad.o

import com.google.firebase.messaging.FirebaseMessagingService
import com.shahad.o.util.log

class MyFirebaseMessagingService : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        token.log()
    }
}