package com.shahad.o.di
//
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.data.dataSources.DataStoreDataSourceImp
import com.shahad.o.data.dataSources.RemoteDataSourceImp
import com.shahad.o.data.dataSources.base.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<Gson> { Gson() }
    single<FirebaseMessaging> { FirebaseMessaging.getInstance() }
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<FirebaseFirestore>{ Firebase.firestore}
    single<DataStoreDataSource> { DataStoreDataSourceImp(androidContext()) }
    single<RemoteDataSource> { RemoteDataSourceImp(get(),get(),get()) }
}