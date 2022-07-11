package com.example.kakaobookapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kakaobookapi.data.model.Book
import com.example.kakaobookapi.data.model.SearchResponse
import com.example.kakaobookapi.data.repository.BookSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookSearchViewModel(
    private val bookSearchRepository: BookSearchRepository
) : ViewModel() {
    //Api
    private val _searchResult = MutableLiveData<SearchResponse>()
    val searchResult: LiveData<SearchResponse> get() = _searchResult

    fun searchBooks(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val response = bookSearchRepository.searchBooks(query, "accuracy", 1, 15)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }

    //SavedState
    var query = String()
        set(value) {
            field = value
            savedStateHandle.set(SAVE_STATE_KEY, value)
        }

    init {
        query = savedStatedHandle.get<String>(SAVE_STATE_KEY) ?: ""
    }

    companion object {
        private const val SAVE_STATE_KEY = "query"
    }

    //Room
    fun saveBook(book: Book) = viewModelScope.launch(Dispatchers.IO) {

    }
}