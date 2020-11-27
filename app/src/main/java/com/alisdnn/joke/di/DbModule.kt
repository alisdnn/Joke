package com.alisdnn.joke.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.alisdnn.data.db.MyDatabase
import com.alisdnn.joke.di.qualifier.ApplicationContext
import javax.inject.Singleton


@Module
class DbModule {

    @Singleton
    @Provides
    fun provideMyDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, MyDatabase::class.java, "mydatabase")
            .build()

    @Singleton
    @Provides
    fun provideJokeDao(myDatabase: MyDatabase) = myDatabase.jokeDao()

    @Singleton
    @Provides
    fun provideJokeCategoryDao(myDatabase: MyDatabase) = myDatabase.categoryDao()
}