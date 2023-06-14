package com.shahad.o.ui.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.shahad.o.BuildConfig
import com.shahad.o.R

//import org.koin.dsl.module

class GoogleAuthUiClient(
    private val context: Context
) {

    fun getClient(): GoogleSignInClient {
        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(BuildConfig.web_id)
                .build()

        return GoogleSignIn.getClient(context, gso)
    }


    fun getSignInResult(intent: Intent, onGetCredential: (AuthCredential) -> Unit) {
        val account = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val result = account.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(result.idToken, null)
            onGetCredential(credential)
        } catch (e: ApiException) {
            Log.i("O_APP", e.message.toString())
        }
    }

}
