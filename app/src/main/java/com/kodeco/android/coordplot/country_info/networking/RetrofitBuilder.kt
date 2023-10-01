package com.kodeco.android.coordplot.country_info.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)
