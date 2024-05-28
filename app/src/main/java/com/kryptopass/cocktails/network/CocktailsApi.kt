package com.kryptopass.cocktails.network

import com.kryptopass.cocktails.BuildConfig
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

data class CocktailsContainer(val drinks: List<Cocktail>?)

data class Cocktail(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)

interface CocktailsApi {

    @GET("filter.php?a=Alcoholic")
    suspend fun getAlcoholic(): ApiResponse<CocktailsContainer>

    companion object Factory {
        fun create(): CocktailsApi {
            val moshi = MoshiConverterFactory.create()

            val client = OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(interceptor)
                }
            }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
                .client(client)
                .addConverterFactory(moshi)
                .build()

            return retrofit.create(CocktailsApi::class.java)
        }
    }
}