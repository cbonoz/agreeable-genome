package com.agreeablegenome.www.agreeablegenome.injection

import com.google.gson.Gson

import com.agreeablegenome.www.agreeablegenome.GenomeApplication
import com.agreeablegenome.www.agreeablegenome.util.GenomeService
import com.agreeablegenome.www.agreeablegenome.util.PrefManager

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
    internal fun providesGenomeService(prefManager: PrefManager): GenomeService {
        return GenomeService(prefManager)
    }

    @Provides
    @Singleton
    internal fun providesPrefManager(app: GenomeApplication, gson: Gson): PrefManager {
        return PrefManager(app, gson)
    }

}
