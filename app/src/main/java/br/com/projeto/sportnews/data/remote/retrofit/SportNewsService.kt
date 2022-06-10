package br.com.projeto.sportnews.data.remote.retrofit

import br.com.projeto.sportnews.domain.New
import retrofit2.Call
import retrofit2.http.GET

interface SportNewsService {
    @GET("sportnews.json")
    fun getNews(): Call<List<New>>
}