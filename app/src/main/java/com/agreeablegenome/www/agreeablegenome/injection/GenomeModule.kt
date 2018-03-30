package com.agreeablegenome.www.agreeablegenome.injection

import com.google.gson.Gson

import com.agreeablegenome.www.agreeablegenome.GenomeApplication
import com.agreeablegenome.www.agreeablegenome.managers.QuizManager
import com.agreeablegenome.www.agreeablegenome.managers.PrefManager
import com.agreeablegenome.www.agreeablegenome.managers.SoundPoolManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides


@Module
class GenomeModule(private val mApplication: GenomeApplication) {

    @Provides
    @Singleton
    internal fun providesApplication(): GenomeApplication {
        return mApplication
    }

    @Provides
    @Singleton
    internal fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    internal fun providesPrefManager(app: GenomeApplication, gson: Gson): PrefManager {
        return PrefManager(app, gson)
    }

}
