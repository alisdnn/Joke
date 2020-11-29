package com.alisdnn.data.datasource.joke

import androidx.paging.DataSource
import io.reactivex.Single
import com.alisdnn.data.datasource.BaseDataSource
import com.alisdnn.domain.entity.Entity


/**
 * Joke database data source
 */
interface JokesDatabaseDataSource : BaseDataSource {

    /**
     * Get all of jokes from database implementation
     */
    fun getJokes(): DataSource.Factory<Int, Entity.Joke>

    /**
     * Persist all of jokes in local database
     */
    fun persist(jokes: List<Entity.Joke>, insertFinished: () -> Unit)

    fun deleteJoke(joke: Entity.Joke): Single<Int>
}