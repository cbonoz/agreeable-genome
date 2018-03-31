package com.agreeablegenome.www.agreeablegenome

import android.app.Application
import android.content.Context
import android.util.Log

import com.agreeablegenome.www.agreeablegenome.injection.DaggerInjectionComponent
import com.agreeablegenome.www.agreeablegenome.injection.InjectionComponent
import com.agreeablegenome.www.agreeablegenome.injection.GenomeModule

class GenomeApplication : Application() {
    private var mInjectionComponent: InjectionComponent? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication", "onCreate")
        mInjectionComponent = DaggerInjectionComponent.builder()
                .genomeModule(GenomeModule(this))
                .build()

        app = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    companion object {

        private var app: GenomeApplication? = null

        val injectionComponent: InjectionComponent
            get() = app!!.mInjectionComponent!!
    }
}
