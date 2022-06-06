package br.com.projeto.sportnews.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.projeto.sportnews.domain.New
import br.com.projeto.sportnews.retrofit.SportNewsRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val service = SportNewsRetrofit.getSportNewsService()
    private val _news = MutableLiveData<List<New>>()

    init {
        loadList()

    }

    private fun loadList() {
        service.getNews().enqueue(object : Callback<List<New>> {
            override fun onResponse(call: Call<List<New>>, response: Response<List<New>>) {
                _news.value = response.body()
                if(response.code() != 200) Log.i("TESTE", "onResponse: " + response.errorBody())
            }

            override fun onFailure(call: Call<List<New>>, t: Throwable) {
                _news.value = emptyList()
                Log.i("TESTE_ERROR", "onFailure: " + t.message)
            }

        })
    }

    val news: LiveData<List<New>> = _news
}