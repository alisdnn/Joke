package com.alisdnn.joke.di.home

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.alisdnn.presentation.ui.home.HomeFragment

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}