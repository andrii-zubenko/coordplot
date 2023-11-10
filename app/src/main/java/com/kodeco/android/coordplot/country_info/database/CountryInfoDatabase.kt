package com.kodeco.android.coordplot.country_info.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodeco.android.coordplot.country_info.database.dao.CountryDao
import com.kodeco.android.coordplot.country_info.models.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryInfoDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CountryInfoDatabase::class.java, "country-info-database"
        ).build()
    }
}