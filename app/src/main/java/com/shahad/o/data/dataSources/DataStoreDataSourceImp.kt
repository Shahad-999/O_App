package com.shahad.o.data.dataSources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.shahad.o.data.dataSources.base.DataStoreDataSource
import kotlinx.coroutines.flow.first

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

    companion object {
        private const val TOKEN_KEY = "TOKEN"
    }
}