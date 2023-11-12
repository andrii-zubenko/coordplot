package com.kodeco.android.coordplot.country_info.di

import CountryAdapter
import android.content.Context
import com.kodeco.android.coordplot.country_info.database.CountryInfoDatabase
import com.kodeco.android.coordplot.country_info.networking.CountryService
import com.kodeco.android.coordplot.country_info.prefdatastore.CountryPrefs
import com.kodeco.android.coordplot.country_info.prefdatastore.CountryPrefsImpl
import com.kodeco.android.coordplot.country_info.repositories.CountryRepository
import com.kodeco.android.coordplot.country_info.repositories.CountryRepositoryImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CountryInfoSingletonModule {

    @Provides
    @Singleton
    fun provideCountryService(): CountryService {
        val moshi = Moshi.Builder()
            .add(CountryAdapter())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(CountryService::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryDatabase(@ApplicationContext applicationContext: Context): CountryInfoDatabase {
        return CountryInfoDatabase.buildDatabase(applicationContext)
    }

    @Provides
    @Singleton
    fun providesCountryRepository(
        service: CountryService,
        database: CountryInfoDatabase,
    ): CountryRepository = CountryRepositoryImpl(service, database.countryDao())

    @Provides
    @Singleton
    fun provideCountryPrefs(@ApplicationContext applicationContext: Context): CountryPrefs {
        return CountryPrefsImpl(applicationContext)
    }
}
