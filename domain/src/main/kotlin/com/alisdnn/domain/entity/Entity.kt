package com.alisdnn.domain.entity


/**
 * Album entity
 */
sealed class Entity {


    data class Joke(
            val id: Long,
            val body: String,
            val categories: List<String>
    ) : Entity()
}