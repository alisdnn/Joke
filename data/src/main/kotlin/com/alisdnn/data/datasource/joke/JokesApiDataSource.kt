package com.alisdnn.data.datasource.joke

import android.annotation.SuppressLint
import io.reactivex.Single
import com.alisdnn.data.api.GenericResponse
import com.alisdnn.data.datasource.BaseDataSource
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity


@SuppressLint("CheckResult")
fun getJokes(
        apiSource: JokesApiDataSource,
        page: Int,
        itemsPerPage: Int,
        onResult: (result: ResultState<List<Entity.Joke>>) -> Unit
) {
    apiSource.getJokes((page - 1) * itemsPerPage, itemsPerPage)
            .subscribe({ data ->
                onResult(ResultState.Success(data))
            }, { throwable ->
                onResult(ResultState.Error(throwable, null))
            })
}

/**
 * Album network data source
 */
interface JokesApiDataSource : BaseDataSource {

    /**
     * Get all of jokes from network
     */
    fun getJokes(page: Int, pageSize: Int): Single<List<Entity.Joke>>
}