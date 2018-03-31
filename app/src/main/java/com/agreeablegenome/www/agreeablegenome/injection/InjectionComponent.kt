package com.agreeablegenome.www.agreeablegenome.injection

import com.agreeablegenome.www.agreeablegenome.activities.*
import com.agreeablegenome.www.agreeablegenome.fragments.FavoritesFragment
import com.agreeablegenome.www.agreeablegenome.fragments.GenomeFragment
import com.agreeablegenome.www.agreeablegenome.fragments.RecipeFragment

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(GenomeModule::class))
interface InjectionComponent {

    // Activities
    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
    fun inject(activity: SplashActivity)

    // Fragments
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(genomeFragment: GenomeFragment)
    fun inject(recipeFragment: RecipeFragment)
}
