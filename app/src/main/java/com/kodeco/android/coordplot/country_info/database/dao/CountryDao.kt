package com.kodeco.android.coordplot.country_info.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kodeco.android.coordplot.country_info.models.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries WHERE isFavorite = 1")
    suspend fun getFavoriteCountries(): List<Country>

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(vararg country: Country)

    @Update
    suspend fun updateFavorite(country: Country)

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()
}