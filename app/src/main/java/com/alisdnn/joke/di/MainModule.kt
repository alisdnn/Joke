package com.alisdnn.joke.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.alisdnn.joke.di.home.HomeFragmentModule
import com.alisdnn.presentation.ui.MainActivity


@Module(includes = [HomeFragmentModule::class])
abstract class MainModule {

    //@PerActivity
    @ContributesAndroidInjector
    abstract fun get(): MainActivity
}