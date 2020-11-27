package com.alisdnn.data.db.joke

import androidx.room.*


/**
 * Album data class
 */
sealed class JokeCategoryData {


    @Entity(tableName = "joke_category_table")
    data class Category(
            @PrimaryKey(autoGenerate = true)
            @ColumnInfo(name = "categoryId") var id: Long,
            @ColumnInfo(name = "title") var title: String
    ) : JokeCategoryData()


}