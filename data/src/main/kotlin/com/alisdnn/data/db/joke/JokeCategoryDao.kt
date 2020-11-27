package com.alisdnn.data.db.joke

import androidx.paging.DataSource
import androidx.room.Dao
import io.reactivex.Flowable
import com.alisdnn.data.db.BaseDao


/**
 * Album dao
 */
@Dao
interface JokeCategoryDao : BaseDao<JokeCategoryData.Category> {

    override fun select(id: Long): Flowable<JokeCategoryData.Category> {
        TODO("Not yet implemented")
    }

    override fun selectAllPaged(): DataSource.Factory<Int, JokeCategoryData.Category> {
        TODO("Not yet implemented")
    }
}