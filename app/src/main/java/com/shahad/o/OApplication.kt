package com.shahad.o

import android.app.Application
import com.shahad.o.di.dataSourceModule
import com.shahad.o.di.repositoryModule
import com.shahad.o.di.useCasesModule
import com.shahad.o.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class OApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@OApplication)
            modules(
                dataSourceModule,
                repositoryModule,
                useCasesModule,
                viewModelModule
            )
        }

    }
}