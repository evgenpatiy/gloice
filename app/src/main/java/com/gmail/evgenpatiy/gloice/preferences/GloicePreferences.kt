package com.gmail.evgenpatiy.gloice.preferences

import android.content.Context
import android.content.SharedPreferences

class GloicePreferences(context: Context) {
    private val preferencesFile: String = "com.gmail.evgenpatiy.gloice_preferences"
    private val lastRequestKey: String = "com.gmail.evgenpatiy.LAST_REQUEST"

    private val mSettings: SharedPreferences =
        context.getSharedPreferences(preferencesFile, Context.MODE_PRIVATE)
    private val mSettingsEditor: SharedPreferences.Editor by lazy {
        mSettings.edit()
    }

    fun getLastRequestId(): Int? {
        return mSettings.getInt(lastRequestKey, 0)
    }

    fun saveLastRequestId(lastRequestId: Int) {
        mSettingsEditor.putInt(lastRequestKey, lastRequestId)
        mSettingsEditor.apply()
    }
}