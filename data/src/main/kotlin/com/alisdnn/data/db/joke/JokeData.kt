package com.alisdnn.data.db.joke

import androidx.room.*


/**
 * Album data class
 */
sealed class JokeData {

    @Entity(tableName = "joke_table")
    data class Joke(
            @ColumnInfo(name = "jokeId") @PrimaryKey(autoGenerate = false) val id: Long,
            @ColumnInfo(name = "body") val body: String
    ) : JokeData()


    @Entity(primaryKeys = ["jokeId", "categoryId"])
    data class JokeCategoryCrossRef(
            val jokeId: Long,
            val categoryId: Long
    ) : JokeData()

    data class JokeWithCategories(
            @Embedded val joke: Joke,
            @Relation(
                    parentColumn = "jokeId",
                    entityColumn = "categoryId",
                    associateBy = Junction(JokeCategoryCrossRef::class)
            )
            val categories: List<JokeCategoryData.Category>
    ) : JokeData()


}