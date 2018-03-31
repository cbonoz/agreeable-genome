package com.agreeablegenome.www.agreeablegenome.injection

import com.agreeablegenome.www.agreeablegenome.activities.*

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(GenomeModule::class))
interface InjectionComponent {

    // Activities
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)
    fun inject(activity: LoginActivity)

    // Fragments

    // Services

    // Other
}
