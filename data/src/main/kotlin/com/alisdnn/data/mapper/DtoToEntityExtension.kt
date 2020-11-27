package com.alisdnn.data.mapper


import com.alisdnn.data.api.GenericResponse
import com.alisdnn.data.api.JokeApi
import com.alisdnn.domain.entity.Entity


/**
 * Extension class to map joke dto to joke entity
 */
fun JokeApi.Dto.Joke.map() = Entity.Joke(
        id = id,
        body = joke,
        categories = categories
)

fun GenericResponse<List<JokeApi.Dto.Joke>>.map(): List<Entity.Joke> {
    val entity: List<Entity.Joke>
    val dao: List<JokeApi.Dto.Joke> = t
    entity = dao.map {
        it.map()
    }
    return entity
}

