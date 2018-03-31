package com.agreeablegenome.www.agreeablegenome.activities

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.agreeablegenome.www.agreeablegenome.GenomeApplication
import com.agreeablegenome.www.agreeablegenome.R
import com.agreeablegenome.www.agreeablegenome.util.GenomeService
import io.github.tonnyl.spark.Spark
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : Activity() {

    @Inject
    lateinit var genomeService: GenomeService

    private lateinit var mSpark: Spark

    private var loading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        GenomeApplication.injectionComponent.inject(this)

        mSpark = Spark.Builder()
                .setView(loginLayout) // View or view group
                .setDuration(5000)
                .setAnimList(Spark.ANIM_GREEN_PURPLE)
                .build()

        loginButton.setOnClickListener {
            setLoading(true)
            // TODO: replace with auth call.
            Handler().postDelayed(this::startMainActivity, 2000)
        }

    }

    private fun setLoading(b: Boolean) {
        loading = b
        if (loading) {
            loadingSpinner.visibility = VISIBLE
            loginButton.visibility = GONE
        } else {
            loadingSpinner.visibility = GONE
            loginButton.visibility = VISIBLE
        }
    }

    fun startMainActivity() {
        genomeService.setToken(genomeService.getRandomTestToken())
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        mSpark.startAnimation()
    }

    override fun onPause() {
        super.onPause()
        mSpark.stopAnimation()
    }


}
