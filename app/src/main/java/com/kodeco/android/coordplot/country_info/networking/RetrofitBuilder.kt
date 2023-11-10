package com.kodeco.android.coordplot.country_info.networking

import CountryAdapter
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val moshi = Moshi.Builder()
    .add(CountryAdapter())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)
