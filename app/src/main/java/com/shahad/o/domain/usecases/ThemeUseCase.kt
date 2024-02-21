package com.shahad.o.domain.usecases

import com.shahad.o.domain.Repository
import kotlinx.coroutines.flow.Flow

class ThemeUseCase(
    private val repository: Repository
) : BaseUseCase() {
    fun isDark(): Flow<Boolean?> = repository.isDarkMode()
    suspend fun updateMode(isDark: Boolean) {
        repository.updateMode(isDark)
    }
}
