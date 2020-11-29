package com.alisdnn.data.datasource.joke

import androidx.paging.DataSource
import com.alisdnn.data.db.joke.JokeDao
import com.alisdnn.data.mapper.map
import com.alisdnn.domain.entity.Entity
import java.util.concurrent.Executor


/**
 * Joke database data source implementation
 */
class JokesDatabaseDataSourceImpl(private val jokeDao: JokeDao,
                                  private val ioExecutor: Executor) : JokesDatabaseDataSource {

    /**
     * Get all of jokes from database implementation
     */
    override fun getJokes(): DataSource.Factory<Int, Entity.Joke> =
            jokeDao.selectAllPagedWithCategories()
                    .map {
                        it.map()
                    }

    /**
     * Persist all of jokes in local database implementation
     */
    override fun persist(jokes: List<Entity.Joke>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            jokeDao.insert/*persist*/(jokes.map { it.map() })
            insertFinished()
        }
    }

    override fun deleteJoke(joke: Entity.Joke) = jokeDao.delete(joke.map())
}