package com.agreeablegenome.www.agreeablegenome.managers

import com.google.gson.Gson
import com.google.gson.JsonParseException

import android.app.Application
import android.content.SharedPreferences

class PrefManager(app: Application, private val gson: Gson) {


    private val settings: SharedPreferences

    init {
        this.settings = app.getSharedPreferences(PREF_NAME, 0)
    }

    // ** Setter methods ** //

    fun saveBoolean(location: String, value: Boolean) {
        val editor = settings.edit()
        editor.putBoolean(location, value)
        editor.apply()
    }

    fun saveString(location: String, value: String) {
        val editor = settings.edit()
        editor.putString(location, value)
        editor.apply()
    }

    fun saveLong(location: String, value: Long) {
        val editor = settings.edit()
        editor.putLong(location, value)
        editor.apply()
    }

    fun saveInt(location: String, value: Int) {
        val editor = settings.edit()
        editor.putInt(location, value)
        editor.apply()
    }

    fun saveJson(key: String, value: Any) {
        val jsonString = gson.toJson(value)
        saveString(key, jsonString)
    }

    // ** Getter methods ** //

    fun getLong(location: String): Long {
        return settings.getLong(location, -1L)
    }

    fun getLong(location: String, defaultValue: Long): Long {
        return settings.getLong(location, defaultValue)
    }

    fun getInt(location: String, defaultValue: Int): Int {
        return settings.getInt(location, defaultValue)
    }

    fun getBoolean(location: String, defaultValue: Boolean? = false): Boolean {
        return settings.getBoolean(location, defaultValue!!)
    }

    fun getString(location: String, defaultValue: String?): String? {
        return settings.getString(location, defaultValue)
    }

    @Throws(JsonParseException::class)
    fun <T> getJson(key: String, objectClass: Class<T>, defaultValue: T): T {
        val value = getString(key, null) ?: return defaultValue
        return gson.fromJson(value, objectClass)
    }

    companion object {
        private val TAG = PrefManager::class.java.simpleName

        private val PREF_NAME = "prefs"
    }

}
