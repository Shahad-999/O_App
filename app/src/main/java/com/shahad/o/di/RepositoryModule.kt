package com.shahad.o.di

import com.shahad.o.data.RepositoryImp
import com.shahad.o.domain.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImp(
            datastoreDataSource = get(),
            remoteDataSource = get(),
            reminderManger = get()
        )
    }
}