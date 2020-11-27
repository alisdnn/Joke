package com.alisdnn.data.mapper

import com.alisdnn.data.db.joke.JokeData
import com.alisdnn.domain.entity.Entity


/**
 * Extension class to map jokes data to joke entity
 */


fun JokeData.JokeWithCategories.map() = Entity.Joke(
        id = joke.id,
        body = joke.body,
        categories = categories.map {
            it.title
        }
)