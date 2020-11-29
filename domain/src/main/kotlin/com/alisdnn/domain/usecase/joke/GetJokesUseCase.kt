package com.alisdnn.domain.usecase.joke

import androidx.paging.PagedList
import io.reactivex.Flowable
import io.reactivex.Single
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity
import com.alisdnn.domain.usecase.BaseUseCase


/**
 * Joke use case
 */
interface GetJokesUseCase : BaseUseCase {

    /**
     * Get all of jokes use case
     */
    fun getJokes(): Flowable<ResultState<PagedList<Entity.Joke>>>

}