package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository

class NotificationsUseCase(
    private val repository: Repository
): BaseUseCase() {
    suspend fun storeNotificationsStatus(isTurnOn: Boolean){
        repository.storeNotificationsStatus(isTurnOn)
    }

    fun updateNotificationStatus(isTurnOn: Boolean){
        repository.changeNotificationsStatus(isTurnOn)
    }

    fun isNotificationsOn() = repository.isNotificationsTurnIn()

}