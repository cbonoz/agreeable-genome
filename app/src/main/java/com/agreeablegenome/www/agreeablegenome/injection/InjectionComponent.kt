package com.agreeablegenome.www.agreeablegenome.injection

import com.agreeablegenome.www.agreeablegenome.activities.MainActivity
import com.agreeablegenome.www.agreeablegenome.activities.SplashActivity
import com.agreeablegenome.www.agreeablegenome.activities.QuizActivity
import com.agreeablegenome.www.agreeablegenome.activities.StatsActivity
import com.agreeablegenome.www.agreeablegenome.fragments.QuizFragment
import com.agreeablegenome.www.agreeablegenome.fragments.QuizResultFragment

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(GenomeModule::class))
interface InjectionComponent {

    // Activities
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)

    // Fragments
//    fun inject(fragment: QuizFragment)
//    fun inject(fragment: QuizResultFragment)


    // Services

    // Other
}
