package com.geetgobindsingh.lifecycleapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import java.util.prefs.Preferences
import androidx.datastore.preferences.preferencesDataStore

private fun sharedPreferencesMigration(context: Context) =
    listOf(SharedPreferencesMigration(context, "Lifecycle"))

private val Context.datastore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "Lifecycle", produceMigrations = ::sharedPreferencesMigration
)

class Pref(context: Context) {
    private val pref = context.datastore
}