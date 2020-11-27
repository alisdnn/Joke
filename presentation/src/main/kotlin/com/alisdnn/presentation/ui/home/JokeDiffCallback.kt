package com.alisdnn.presentation.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.alisdnn.domain.entity.Entity


class JokeDiffCallback : DiffUtil.ItemCallback<Entity.Joke>() {

    override fun areItemsTheSame(oldItem: Entity.Joke, newItem: Entity.Joke): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Entity.Joke, newItem: Entity.Joke): Boolean =
            oldItem == newItem

    override fun getChangePayload(oldItem: Entity.Joke, newItem: Entity.Joke): Any? {
        // Return particular field for changed item.
        return super.getChangePayload(oldItem, newItem)
    }
}