package com.example.book.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.data.PotterBook
import com.example.book.data.PotterBookItem
import com.example.book.network.BookApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class BookApiStatus { LOADING, ERROR, DONE }

class BookViewModel : ViewModel() {
    private val _status = MutableLiveData<BookApiStatus>()
    val status: LiveData<BookApiStatus> = _status

    private val _book = MutableLiveData<List<PotterBookItem>>()
    val book: LiveData<List<PotterBookItem>> = _book

    private val _books = MutableLiveData<PotterBookItem>()
    val books: LiveData<PotterBookItem> = _books

    fun listToString(list: List<String>): String {
        return list.joinToString("\n")
    }

    fun getBookList() {
        viewModelScope.launch {
            _status.value = BookApiStatus.LOADING
            try {
                _book.value = BookApi.retrofitBookApi.getBook()
                _status.value = BookApiStatus.DONE
            } catch (e: Exception) {
                _book.value = listOf()
                _status.value = BookApiStatus.ERROR
            }
        }
    }

    fun onBookClicked(book: PotterBookItem) {
        _books.value = book
    }


}