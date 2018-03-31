package com.agreeablegenome.www.agreeablegenome.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.agreeablegenome.www.agreeablegenome.GenomeApplication

import com.agreeablegenome.www.agreeablegenome.R
import com.agreeablegenome.www.agreeablegenome.util.GenomeService
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.bumptech.glide.Glide
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.ybq.android.spinkit.SpinKitView
import javax.inject.Inject

class RecipeFragment : Fragment() {

    @Inject
    lateinit var genomeService: GenomeService

    private var loading: Boolean = false

    lateinit var recipeSpinner: SpinKitView
    lateinit var recipeImage: ImageView
    lateinit var recipeText: TextView
    lateinit var genomeText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        GenomeApplication.injectionComponent.inject(this)

        recipeSpinner = view.findViewById(R.id.recipeSpinner)
        recipeImage = view.findViewById(R.id.recipeImage)
        recipeText = view.findViewById(R.id.recipeText)
        genomeText = view.findViewById(R.id.genomeText)

        getTodaysRecipe()

        return view
    }

    private fun setLoading(b: Boolean) {
        loading = b
        if (loading) {
            recipeSpinner.visibility = View.VISIBLE
            recipeImage.visibility = View.GONE
        } else {
            recipeSpinner.visibility = View.GONE
            recipeImage.visibility = View.VISIBLE
        }
    }

    // TODO: replace with single call to get the current recipe for today (new one generated each time for demo purposes).
    private fun getTodaysRecipe() {
        loading = true
        setLoading(true)
        val token = genomeService.getToken()
        val header = mapOf("Authorization" to "Bearer ${token}")
        val genomeItem = genomeService.getRandomGenomeItem()
        genomeService.getReportUrl(genomeItem).httpGet().header(header).responseString { _, _, genomeResult ->
            when (genomeResult) {
                is Result.Failure -> {
                    val ex = genomeResult.getException()
                    Toast.makeText(activity, ex.localizedMessage, Toast.LENGTH_SHORT).show()
                    loading = false
                }
                is Result.Success -> {
                    val data = genomeResult.get()
                    Log.d("genome", data)
                    val parser = Parser()
                    val stringBuilder = StringBuilder(data)
                    val json = parser.parse(stringBuilder) as JsonObject
                    val summary = json.obj("summary")
                    val score = summary?.int("score")
                    val text = summary?.string("text")

                    val displayName = json.obj("phenotype")?.string("display_name")

                    genomeText.text = "${displayName}\nRating ${score}: ${text}"

                    // TODO: get recipe using the given report property as a param.
                    genomeService.getRecipeUrl(displayName).httpGet().responseString { _, _, recipeResult ->
                        when (recipeResult) {
                            is Result.Failure -> {
                                val ex = recipeResult.getException()
                                Toast.makeText(activity, ex.localizedMessage, Toast.LENGTH_SHORT).show()
                                loading = false
                            }
                            is Result.Success -> {
                                val recipeData = recipeResult.get()
                                Log.d("recipe", recipeData)
                                loading = false
                                renderImageIntoView(recipeData)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun renderImageIntoView(recipeData: String) {
        val parser = Parser()
        val stringBuilder = StringBuilder(recipeData)
        val json = parser.parse(stringBuilder) as JsonObject
        val hits = json.array<JsonObject>("hits")
        if (hits == null || hits.isEmpty()) {
            // TODO: improve handling
            recipeText.text = getString(R.string.no_recipe_found)
            return
        }
        // Take the first recipe result from the search.
        val recipe = hits.get(0).get("recipe") as JsonObject

        val recipeImageUrl = recipe.string("image")
        val description = StringBuilder()
        description.append(recipe.string("label")).append("\n")
        description.append(recipe.string("source")).append("\n")
        description.append("Calories: ").append(recipe.float("Calories")).append("\n")
        recipeText.text = description.toString()

        val fullDescription = StringBuilder()
        for (key in recipe.keys) {
            val value = recipe.get("key") as String
            if (!value.contains("www")) {
                fullDescription.append(key).append(": ").append(value).append("\n")
            }
        }

        Glide.with(this)
                .load(recipeImageUrl)
                .into(recipeImage);

        recipeImage.setOnClickListener {
            if (activity != null)
                MaterialDialog.Builder(activity!!.applicationContext)
                        .title("Today's Recipe")
                        .content(fullDescription.toString())
                        .positiveText("I made this")
                        .negativeText("Skip")
                        .icon(resources.getDrawable(R.drawable.zzz_food))
                        .show()
        }
    }


}
