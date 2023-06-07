package com.shahad.o.di
//
import com.google.firebase.auth.FirebaseAuth
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.data.dataSources.DataStoreDataSourceImp
import com.shahad.o.data.dataSources.RemoteDataSourceImp
import com.shahad.o.data.dataSources.base.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<DataStoreDataSource> { DataStoreDataSourceImp(androidContext()) }
    single<RemoteDataSource> { RemoteDataSourceImp(get()) }
}