package br.com.projeto.sportnews.retrofit

import br.com.projeto.sportnews.domain.New
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface SportNewsService {
    @GET("sportnews.json")
    fun getNews(): Call<List<New>>
}