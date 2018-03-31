package com.agreeablegenome.www.agreeablegenome.util

import com.agreeablegenome.www.agreeablegenome.BuildConfig
import java.util.*

class GenomeService(private val prefManager: PrefManager) {

    val random = Random()

    fun getReportUrl(item: String): String {
        return "${GENOME_URL}/reports/${item}?population=${POPULATION}"
    }

    fun getRecipeUrl(item: String?): String {
        val query: String
        if (item != null) {
            query = item
        } else {
            query = ""
        }
        return "${EDAMUM_URL}?q=${query}&app_id=${BuildConfig.EdamamAppId}&app_key=${BuildConfig.EdamamApiKey}"
    }

    fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }

    val genomeItems = listOf<String>(
            "blood-glucose",
            "beta-carotene",
            "alpha-linolenic-acid",
            "iron",
            "phosphorus",
            "magnesium",
            "calcium",
            "folate",
            "response-to-vitamin-e-supplementation",
            "vitamin-e",
            "vitamin-d",
            "vitamin-b12",
            "vitamin-a",
            "milk-allergy",
            "peanuts-allergy",
            "egg-allergy",
            "alcohol-drinking-behavior",
            "caffeine-consumption",
            "caffeine-metabolite-ratio",
            "bitter-taste",
            "smoking-behavior",
            "smell-sensitivity-for-malt",
            "white-wine-liking",
            "red-wine-liking",
            "carbohydrate-intake",
            "protein-intake",
            "sleep-duration",
            "waist",
            "visceral-and-subcutaneous-adipose-tissue-ratio",
            "body-fat-percentage",
            "lean-body-mass",
            "body-fat-mass",
            "bmi",
            "weight"
    )

    fun getRandomGenomeItem(): String {
        val num = rand(0, genomeItems.size)
        return genomeItems.get(num)
    }

    fun getRandomTestToken(): String {
        val num = rand(1, 5) // Precreated random users."
        return "GENOMELINKTEST00${num}"
    }

    fun getUserFromPref(): User {
        return prefManager.getJson("user", User::class.java, User("Test", null, true, mapOf()))
    }

    fun saveUser(user: User) {
        prefManager.saveJson("user", user)
    }

    fun setToken(token: String?) {
        prefManager.saveString("token", token)
    }

    fun getToken(): String? {
        return prefManager.getString("token", null)
    }

    fun logout() {
        prefManager.saveBoolean("firstLogin", true)
        setToken(null)
    }

    companion object {
        val GENOME_URL = "https://genomelink.io/v1"
        val EDAMUM_URL = "https://api.edamam.com/search"
        val EXAMPLE_EDAMUM_URL = "https://api.edamam.com/search?q=chicken&app_id=${BuildConfig.EdamamAppId}&app_key=${BuildConfig.EdamamApiKey}&from=0&to=3&calories=591-722&health=alcohol-free"
        val POPULATION = "european"
    }

}