package com.alisdnn.data.repository.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alisdnn.data.datasource.joke.JokesApiDataSource
import com.alisdnn.data.datasource.joke.JokesDatabaseDataSource
import com.alisdnn.data.datasource.joke.getJokes
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity

class RepoBoundaryCallback(
        private val apiSource: JokesApiDataSource,
        private val databaseSource: JokesDatabaseDataSource
) : PagedList.BoundaryCallback<Entity.Joke>() {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1
    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should send request to the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to send a request to the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Entity.Joke) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        getJokes(apiSource, lastRequestedPage, NETWORK_PAGE_SIZE) { jokes ->
            when (jokes) {
                is ResultState.Success -> {
                    databaseSource.persist(jokes.data) {
                        lastRequestedPage++
                        isRequestInProgress = false
                    }
                }
                is ResultState.Error -> {
                    _networkErrors.postValue(jokes.throwable.message)
                    isRequestInProgress = false
                }
            }
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}