package com.shahad.o.di

import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.data.dataSources.DataStoreDataSourceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<DataStoreDataSource> { DataStoreDataSourceImp(androidContext()) }
}