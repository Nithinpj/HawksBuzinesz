package com.hawks.hawksbuziness.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hawks.hawksbuziness.model.profile.Auth

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfole(auth: Auth)

    @Query("SELECT * FROM Auth")
    fun getProfile():LiveData<Auth>

    @Query("delete from auth")
    suspend fun clearProfile()


}