package com.agreeablegenome.www.agreeablegenome.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.agreeablegenome.www.agreeablegenome.R

class GenomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_genome, container, false)

        getGenomeInfo()

        return view
    }

    private fun getGenomeInfo() {

    }


}
