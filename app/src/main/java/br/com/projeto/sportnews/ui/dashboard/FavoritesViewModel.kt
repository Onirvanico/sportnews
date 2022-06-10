package br.com.projeto.sportnews.ui.dashboard

import androidx.lifecycle.*
import br.com.projeto.sportnews.data.local.NewDAO
import br.com.projeto.sportnews.domain.New
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritesViewModel(private val dao: NewDAO) : ViewModel() {


    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getFavorites() {
      viewModelScope.launch {
          dao.getNews().flowOn(Dispatchers.Main)
              .onStart {
                    _state.value = State.Loading
              }
              .catch {
                  _state.value = State.Failure(it)
              }
              .collect {
                  _state.value = State.Success(it)
              }

      }
    }

    fun saveNew(new: New) {
        viewModelScope.launch {
            dao.saveFavorite(new)
        }
    }

    suspend fun hasNew(new: New): Int {
        return dao.hasNew(new.id)
    }

    fun removeNew(new: New) {
        viewModelScope.launch {
            dao.removeFavorite(new)
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val result : List<New>) : State()
        data class Failure(val error: Throwable) : State()

    }
}

class FavoriteViewModelFactory(private val dao: NewDAO) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavoritesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(dao) as T
        }

        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }

}