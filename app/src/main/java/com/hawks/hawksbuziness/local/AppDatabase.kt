package com.hawks.hawksbuziness.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hawks.hawksbuziness.local.dao.CategoryDao
import com.hawks.hawksbuziness.local.dao.LangugeDao
import com.hawks.hawksbuziness.local.dao.PlaceDao
import com.hawks.hawksbuziness.local.dao.ProfileDao
import com.hawks.hawksbuziness.model.category.Data
import com.hawks.hawksbuziness.model.profile.Auth

//
@Database(
    entities = [Auth::class,Data::class,com.hawks.hawksbuziness.model.places.Data::class,com.hawks.hawksbuziness.model.languages.Data::class],
    version = 1,
    exportSchema = false
)
abstract class HawksDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun categoryDao():CategoryDao
    abstract fun placeDao():PlaceDao
    abstract fun languageDao():LangugeDao

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
