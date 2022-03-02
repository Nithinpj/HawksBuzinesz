package com.nithi.hawksbuziness.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nithi.hawksbuziness.local.dao.ProfileDao
import com.nithi.hawksbuziness.model.profile.Auth
//
@Database(
    entities = [Auth::class],
    version = 1,
    exportSchema = false
)
abstract class HawksDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var instance: HawksDatabase? = null

        fun getDatabase(context: Context): HawksDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, HawksDatabase::class.java, "hawksbuziness")
                .fallbackToDestructiveMigration()
                .build()
    }
}
