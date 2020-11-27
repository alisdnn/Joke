package com.alisdnn.data.db.joke

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Flowable
import com.alisdnn.data.db.BaseDao


/**
 * Album dao
 */
@Dao
interface JokeDao : BaseDao<JokeData.Joke> {

    @Query("SELECT * FROM joke_table WHERE jokeId = :id")
    override fun select(id: Long): Flowable<JokeData.Joke>

    @Query("SELECT * FROM joke_table ORDER BY jokeId")
    override fun selectAllPaged(): DataSource.Factory<Int, JokeData.Joke>

    @Transaction
    @Query("SELECT * FROM joke_table")
    fun selectAllPagedWithCategories(): DataSource.Factory<Int, JokeData.JokeWithCategories>
}