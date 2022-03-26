package com.hawks.hawksbuziness.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hawks.hawksbuziness.model.languages.Data

@Dao
interface LangugeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguage(languge:List<Data>)

    @Query("select * from language")
    fun getAllLanguges(): LiveData<List<Data>>
}