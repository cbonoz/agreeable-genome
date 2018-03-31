package com.agreeablegenome.www.agreeablegenome.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.agreeablegenome.www.agreeablegenome.GenomeApplication

import com.agreeablegenome.www.agreeablegenome.R
import com.agreeablegenome.www.agreeablegenome.util.GenomeService
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
    }


}
