package com.agreeablegenome.www.agreeablegenome.activities

import android.content.Intent
import android.util.Log

import com.daimajia.androidanimations.library.Techniques
import com.agreeablegenome.www.agreeablegenome.BuildConfig
import com.agreeablegenome.www.agreeablegenome.GenomeApplication
import com.agreeablegenome.www.agreeablegenome.R
import com.agreeablegenome.www.agreeablegenome.util.PrefManager
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AwesomeSplash() {

    @Inject
    lateinit var prefManager: PrefManager

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!
    override fun initSplash(configSplash: ConfigSplash) {
        /* you don't have to override every property */
        GenomeApplication.injectionComponent.inject(this)

        val duration: Int
        if (BuildConfig.DEBUG) {
            duration = 500
        } else {
            // Production duration for into splash screen animations.
            duration = 1000
        }
        Log.d(TAG, "Splash duration: " + duration)

        //Customize Circular Reveal
        configSplash.backgroundColor = R.color.material_deep_teal_200
        configSplash.animCircularRevealDuration = duration //int ms
        configSplash.revealFlagX = Flags.REVEAL_LEFT  //or Flags.REVEAL_RIGHT
        configSplash.revealFlagY = Flags.REVEAL_TOP //or Flags.REVEAL_BOTTOM

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.logoSplash = R.drawable.genome_logo_trans_170
        configSplash.animLogoSplashDuration = duration //int ms
        configSplash.animLogoSplashTechnique = Techniques.FadeIn //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash.titleSplash = getString(R.string.slogan)
        configSplash.titleTextColor = R.color.material_grey_600
        configSplash.titleTextSize = 18f //float value
        configSplash.animTitleDuration = duration
        configSplash.animTitleTechnique = Techniques.FadeInDown
        // configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/

    }

    override fun animationsFinished() {
        // Transit to another activity here or perform other actions.

        val token = prefManager.getString("token", null)
        val intent: Intent
        if (token != null) {
            intent = Intent(this, MainActivity::class.java)
        } else {
            intent = Intent(this, LoginActivity::class.java)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    companion object {
        private val TAG = "SplashActivity"
    }

}
