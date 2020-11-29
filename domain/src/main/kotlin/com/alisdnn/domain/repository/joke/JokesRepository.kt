package com.alisdnn.domain.repository.joke

import androidx.paging.PagedList
import io.reactivex.Flowable
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity
import com.alisdnn.domain.repository.BaseRepository


/**
 * Joke repository
 */
interface JokesRepository : BaseRepository {

    /**
     * Perform
     */
    fun getJokes(): Flowable<ResultState<PagedList<Entity.Joke>>>

}