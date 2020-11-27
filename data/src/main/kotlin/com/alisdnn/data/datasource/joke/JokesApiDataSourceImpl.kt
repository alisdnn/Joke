package com.alisdnn.data.datasource.joke

import io.reactivex.Single
import com.alisdnn.data.api.JokeApi
import com.alisdnn.data.common.extension.applyIoScheduler
import com.alisdnn.data.mapper.map
import com.alisdnn.domain.entity.Entity


/**
 * Joke network data source implementation
 */
class JokesApiDataSourceImpl(private val api: JokeApi) : JokesApiDataSource {

    /**
     * Get all of jokes from network implementation
     */
    override fun getJokes(page: Int, pageSize: Int): Single<List<Entity.Joke>> =
            api.getJokes()
                    .applyIoScheduler()
                    .map {
                        it.map()
                    }

}