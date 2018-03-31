package com.agreeablegenome.www.agreeablegenome.util

import java.util.*

import android.app.Activity
import android.util.DisplayMetrics
import java.util.*

class GenomeService(private val prefManager: PrefManager) {

    val random = Random()

    fun rand(from: Int, to: Int): Int {

        return random.nextInt(to - from) + from
    }

    val genomeItems = listOf<String>(
            "report:blood-glucose",
            "report:beta-carotene",
            "report:alpha-linolenic-acid",
            "report:iron",
            "report:phosphorus",
            "report:magnesium",
            "report:calcium",
            "report:folate",
            "report:response-to-vitamin-e-supplementation",
            "report:vitamin-e",
            "report:vitamin-d",
            "report:vitamin-b12",
            "report:vitamin-a",
            "report:milk-allergy",
            "report:peanuts-allergy",
            "report:egg-allergy",
            "report:alcohol-drinking-behavior",
            "report:caffeine-consumption",
            "report:caffeine-metabolite-ratio",
            "report:bitter-taste",
            "report:smoking-behavior",
            "report:smell-sensitivity-for-malt",
            "report:white-wine-liking",
            "report:red-wine-liking",
            "report:carbohydrate-intake",
            "report:protein-intake",
            "report:sleep-duration",
            "report:waist",
            "report:visceral-and-subcutaneous-adipose-tissue-ratio",
            "report:body-fat-percentage",
            "report:lean-body-mass",
            "report:body-fat-mass",
            "report:bmi",
            "report:weight")

    fun getRandomTestToken(): String {
        val num = rand(1, 5) // Precreated random users."
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