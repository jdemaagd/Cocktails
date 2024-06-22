package com.kryptopass.movie.data

import com.kryptopass.movie.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private const val BASE_URL = BuildConfig.TMDB_BASE_URL

    private val retrofit by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}
