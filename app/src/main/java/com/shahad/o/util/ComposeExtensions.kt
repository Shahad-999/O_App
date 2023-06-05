package com.shahad.o.util

import androidx.navigation.NavHostController
import com.shahad.o.ui.navigation.Screens

fun NavHostController.go(source: Screens,destination: Screens){
    navigate(destination.route) {
        popUpTo(source.route) {
            inclusive = true
        }
    }
}