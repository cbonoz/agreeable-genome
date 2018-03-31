package com.agreeablegenome.www.agreeablegenome.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agreeablegenome.www.agreeablegenome.GenomeApplication

import com.agreeablegenome.www.agreeablegenome.R
import com.agreeablegenome.www.agreeablegenome.util.GenomeService
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import javax.inject.Inject

class RecipeFragment : Fragment() {


    @Inject
    lateinit var genomeService: GenomeService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)
        GenomeApplication.injectionComponent.inject(this)

        getTodaysRecipe()

        return view
    }

    private fun getTodaysRecipe() {
        genomeService.getReportUrl(genomeService.getRandomGenomeItem()).httpGet().responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.
                }
            }
        }


    }


}
