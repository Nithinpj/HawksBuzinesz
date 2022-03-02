package com.nithi.hawksbuziness.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nithi.hawksbuziness.model.profile.Auth

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfole(auth: Auth)

    @Query("SELECT * FROM Auth")
    fun getProfile():LiveData<Auth>

    @Delete
    suspend fun clearProfile(auth: Auth)


}