package com.alisdnn.presentation.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alisdnn.domain.common.ResultState
import com.alisdnn.domain.entity.Entity
import com.alisdnn.presentation.R
import com.alisdnn.presentation.common.extension.observe
import com.alisdnn.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var isLoading = false

    private val adapter: JokeListAdapter by lazy {
        JokeListAdapter()
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }



    private fun showJokes(jokes: ResultState<PagedList<Entity.Joke>>) {
        when (jokes) {
            is ResultState.Success -> {
                hideLoading()
                adapter.submitList(jokes.data)
            }
            is ResultState.Error -> {
                hideLoading()
                Toast.makeText(activity, jokes.throwable.message, Toast.LENGTH_SHORT).show()
                adapter.submitList(jokes.data)
            }
            is ResultState.Loading -> {
                adapter.submitList(jokes.data)
            }
        }
        isLoading = false
        fragmentHomeSwp.isRefreshing = false
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        fragmentHomeSwp.isRefreshing = true
        fragmentHomeSwp.setOnRefreshListener(this)
        fragmentHomeRcyMain.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        fragmentHomeRcyMain.setHasFixedSize(true)
        fragmentHomeRcyMain.adapter = adapter


        showLoading()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? = inflater.inflate(
            R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe(viewModel.jokesLiveData, ::showJokes)
        viewModel.getJokes()
    }

    override fun onRefresh() {
    }
}