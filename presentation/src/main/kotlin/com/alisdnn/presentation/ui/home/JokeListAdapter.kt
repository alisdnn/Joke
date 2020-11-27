package com.alisdnn.presentation.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import com.alisdnn.domain.entity.Entity
import com.alisdnn.presentation.R
import com.alisdnn.presentation.databinding.ItemJokeBinding


class JokeListAdapter : PagedListAdapter<Entity.Joke, JokeListAdapter.DataHolder>(JokeDiffCallback()) {

    private val onJokeItemClickSubject = PublishSubject.create<Entity.Joke>()
    val jokeItemClickEvent: Observable<Entity.Joke> = onJokeItemClickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val bind: ItemJokeBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
                R.layout.item_joke, parent, false) as ItemJokeBinding

        return DataHolder(bind)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val photoResult = getItem(position)
        photoResult?.let {
            holder.bind(photoResult)
        }
    }

    inner class DataHolder(private var ItemJokeBinding: ItemJokeBinding) : RecyclerView.ViewHolder
    (ItemJokeBinding.root), View.OnClickListener {

        fun bind(jokeItem: Entity.Joke) {
            ItemJokeBinding.jokeEntity = jokeItem
            ItemJokeBinding.root.setOnClickListener(this)
            ItemJokeBinding.executePendingBindings()
        }

        override fun onClick(view: View) {
            val joke = getItem((adapterPosition))
            joke?.let {
                val product: Entity.Joke = joke
                onJokeItemClickSubject.onNext(product)
            }
        }
    }
}