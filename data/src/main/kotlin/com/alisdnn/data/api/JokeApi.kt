package com.alisdnn.data.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET


/**
 * Jokes api
 */
interface JokeApi {

    /**
     * Get all jokes
     */
    @GET("jokes/random/10")
    fun getJokes(): Single<GenericResponse<List<Dto.Joke>>>

    sealed class Dto {

        data class Joke(
                @SerializedName("id") val id: Long,
                @SerializedName("joke") val joke: String,
                @SerializedName("categories") val categories: List<String>
        ) : Dto()

    }
}