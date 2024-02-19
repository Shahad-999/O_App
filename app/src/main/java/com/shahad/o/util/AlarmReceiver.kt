package com.shahad.o.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.shahad.o.data.dataSources.DataStoreDataSourceImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val reminderManger : ReminderManger  by inject(ReminderManger::class.java)
        val dataStoreDataSourceImp = DataStoreDataSourceImp(context)
        goAsync {
            dataStoreDataSourceImp.updateTodayStatus(false)

        }
        reminderManger.startReminder()
    }
    private fun BroadcastReceiver.goAsync(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val pendingResult = goAsync()
        @OptIn(DelicateCoroutinesApi::class) // Must run globally; there's no teardown callback.
        GlobalScope.launch(context) {
            try {
                block()
            } finally {
                pendingResult.finish()
            }
        }
    }
}

