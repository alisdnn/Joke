package com.alisdnn.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import io.reactivex.disposables.Disposable
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity
import com.alisdnn.domain.usecase.joke.GetJokesUseCase
import com.alisdnn.presentation.common.OperationLiveData
import com.alisdnn.presentation.ui.base.BaseViewModel
import javax.inject.Inject


class HomeViewModel
@Inject
constructor(
        private val getJokesUseCase: GetJokesUseCase
) : BaseViewModel() {

    private var tempDispossable: Disposable? = null

    private val fetch = MutableLiveData<String>()


    val jokesLiveData: LiveData<ResultState<PagedList<Entity.Joke>>> = Transformations.switchMap(fetch) {
        OperationLiveData<ResultState<PagedList<Entity.Joke>>> {

            if (tempDispossable?.isDisposed != true)
                tempDispossable?.dispose()
            tempDispossable = getJokesUseCase.getJokes().subscribe { resultState ->
                postValue((resultState))
            }
            tempDispossable?.track()
        }
    }


    fun getJokes() {
        fetch.postValue("")
        //page++
    }

}