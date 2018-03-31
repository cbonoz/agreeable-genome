package com.agreeablegenome.www.agreeablegenome.util

import android.app.Activity
import android.util.DisplayMetrics
import java.util.*


class GenomeService(private val prefManager: PrefManager) {

    val random = Random()

    fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    fun getRandomTestToken(): String {
        val num = rand(1,5) // Precreated random users.
        return "GENOMELINKTEST00${num}"
    }

    fun getToken(): String? {
        return prefManager.getString("token", null)
    }

    fun setToken(token: String?) {
        prefManager.saveString("token", token)
    }

    fun logout() {
        setToken(null)
        prefManager.saveBoolean("firstLogin", true)
    }

}