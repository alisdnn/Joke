package com.alisdnn.data.repository.joke

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import com.alisdnn.data.common.extension.applyIoScheduler
import com.alisdnn.data.datasource.joke.JokesApiDataSource
import com.alisdnn.data.datasource.joke.JokesDatabaseDataSource
import com.alisdnn.data.repository.BaseRepositoryImpl
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity
import com.alisdnn.domain.repository.joke.JokesRepository


/**
 * Joke repository implementation
 */
class JokesRepositoryImpl(
        private val apiSource: JokesApiDataSource,
        private val databaseSource: JokesDatabaseDataSource
) : BaseRepositoryImpl<Entity.Joke>(), JokesRepository {

    /**
     * Perform implementation
     */
    override fun getJokes(): Flowable<ResultState<PagedList<Entity.Joke>>> {
        val dataSourceFactory = databaseSource.getJokes()
        val boundaryCallback = RepoBoundaryCallback(apiSource, databaseSource)
        val data = RxPagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .buildFlowable(BackpressureStrategy.BUFFER)

        return data
                .applyIoScheduler()
                .map { d ->
                    if (d.size > 0)
                        ResultState.Success(d) as ResultState<PagedList<Entity.Joke>>
                    else
                        ResultState.Loading(d) as ResultState<PagedList<Entity.Joke>>
                }
                .onErrorReturn { e -> ResultState.Error(e, null) }
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}