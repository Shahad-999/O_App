package com.shahad.o.data.dataSources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import com.shahad.o.util.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "O_PREFERENCES"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)


class DataStoreDataSourceImp(
    private val context: Context
) : DataStoreDataSource {

    override suspend fun saveToken(token: String) {
        val preferencesKey = stringPreferencesKey(TOKEN_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = token
        }
    }

    override suspend fun getToken(): String? {
        val preferencesKey = stringPreferencesKey(TOKEN_KEY)
        val preferences = context.dataStore.data.first()
        return preferences[preferencesKey]
    }

    override suspend fun deleteToken() {
        val preferencesKey = stringPreferencesKey(TOKEN_KEY)
        context.dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }
    }

    override suspend fun updateMode(isDark: Boolean) {
        val preferencesKey = booleanPreferencesKey(THEME_MODE_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = isDark
        }
    }

    override fun isDarkMode(): Flow<Boolean> {
        val preferencesKey = booleanPreferencesKey(THEME_MODE_KEY)
        return context.dataStore.data.map {
            it[preferencesKey].log() == true
        }
    }

    override suspend fun updateTodayStatus(isDone: Boolean) {
        val preferencesKey = booleanPreferencesKey(TODAY_STATUS_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = isDone
        }
    }

    override fun isTodayDone(): Flow<Boolean> {
        val preferencesKey = booleanPreferencesKey(TODAY_STATUS_KEY)
        return context.dataStore.data.map {
            it[preferencesKey].log() == true
        }
    }

    override suspend fun updateNotificationStatus(isTurn: Boolean) {
        val preferencesKey = booleanPreferencesKey(NOTIFICATION_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = isTurn
        }
    }

    override fun isNotificationsTurnOn(): Flow<Boolean> {
        val preferencesKey = booleanPreferencesKey(NOTIFICATION_KEY)
        return context.dataStore.data.map {
            it[preferencesKey].log() == true
        }
    }

    companion object {
        private const val TOKEN_KEY = "TOKEN"
        private const val THEME_MODE_KEY = "THEME_MODE"
        private const val NOTIFICATION_KEY = "NOTIFICATION"
        private const val TODAY_STATUS_KEY = "TODAY_STATUS"
    }
}