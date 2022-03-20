package com.hawks.hawksbuziness.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hawks.hawksbuziness.model.places.Data

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlace(place:List<Data>)

    @Query("select * from place")
    fun getAllPlaces():LiveData<List<Data>>
}