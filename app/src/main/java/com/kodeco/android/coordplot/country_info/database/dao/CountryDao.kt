package com.kodeco.android.coordplot.country_info.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kodeco.android.coordplot.country_info.models.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries WHERE isFavorite = 1")
    suspend fun getFavoriteCountries(): List<Country>

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Query("UPDATE countries SET isFavorite = :isFavorite WHERE commonName = :commonName")
    suspend fun updateFavorite(commonName: String, isFavorite: Boolean)
}