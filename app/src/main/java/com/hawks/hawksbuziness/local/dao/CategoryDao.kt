package com.hawks.hawksbuziness.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hawks.hawksbuziness.model.category.Data

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(data:List<Data>)

    @Query("select * from categories")
    fun getAllCategories():LiveData<List<Data>>

    @Query("delete from categories")
    suspend fun deleteAll()
}