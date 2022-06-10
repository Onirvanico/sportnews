package br.com.projeto.sportnews.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SportNewsRetrofit {
    private const val BASE_URL = "https://onirvanico.github.io/sportnews-api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getSportNewsService() = retrofit.create(SportNewsService::class.java)
}