package com.alisdnn.data.mapper

import com.alisdnn.data.db.joke.JokeData
import com.alisdnn.domain.entity.Entity


/**
 * Extension class to map joke entity to joke data
 */
fun Entity.Joke.map() = JokeData.Joke(
        id = id,
        body = body
)