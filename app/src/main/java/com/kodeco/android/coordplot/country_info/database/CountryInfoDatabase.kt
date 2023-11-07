package com.kodeco.android.coordplot.country_info.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kodeco.android.coordplot.country_info.database.dao.CountryDao
import com.kodeco.android.coordplot.country_info.models.Country

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountryInfoDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}