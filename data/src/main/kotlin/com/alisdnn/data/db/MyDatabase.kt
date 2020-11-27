package com.alisdnn.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alisdnn.data.db.joke.JokeCategoryDao
import com.alisdnn.data.db.joke.JokeCategoryData
import com.alisdnn.data.db.joke.JokeDao
import com.alisdnn.data.db.joke.JokeData


/**
 * Database class with all of its dao classes
 */
@Database(entities = [JokeData.Joke::class, JokeCategoryData.Category::class, JokeData.JokeCategoryCrossRef::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun jokeDao(): JokeDao

    abstract fun categoryDao(): JokeCategoryDao
}