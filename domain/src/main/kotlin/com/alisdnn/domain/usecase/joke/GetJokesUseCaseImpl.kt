package com.alisdnn.domain.usecase.joke

import androidx.paging.PagedList
import io.reactivex.Flowable
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.common.transformer.FTransformer
import com.alisdnn.domain.common.transformer.STransformer
import com.alisdnn.domain.entity.Entity
import com.alisdnn.domain.repository.joke.JokesRepository


/**
 * Album use case implementation
 */
class GetJokesUseCaseImpl(
        private val transformerFlowable: FTransformer<ResultState<PagedList<Entity.Joke>>>,
        private val transformerSingle: STransformer<ResultState<Int>>,
        private val transformerSingleList: STransformer<ResultState<List<Entity.Joke>>>,
        private val repository: JokesRepository) : GetJokesUseCase {

    /**
     * Get all of jokes use case implementation
     */
    override fun getJokes(): Flowable<ResultState<PagedList<Entity.Joke>>> =
            repository.getJokes()/*.compose(transformerFlowable)*/

}