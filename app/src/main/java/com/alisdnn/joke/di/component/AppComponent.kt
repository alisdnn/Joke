package com.alisdnn.joke.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.alisdnn.joke.MyApplication
import com.alisdnn.joke.di.AppModule
import com.alisdnn.joke.di.MainModule
import com.alisdnn.joke.di.ViewModelModule
import com.alisdnn.joke.di.home.HomeModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    AppModule::class,
    MainModule::class,
    HomeModule::class
])
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}