package br.com.projeto.sportnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.projeto.sportnews.domain.New

class NewsViewModel : ViewModel() {

    private val _news = MutableLiveData<List<New>>().apply {
    value = listOf(
        New(title = "title1", description = "description1"),
        New(title = "title2", description = "description2"),
        New(title = "title3", description = "description3"),
        New(title = "title4", description = "description4"))
    }
    val news: LiveData<List<New>> = _news
}