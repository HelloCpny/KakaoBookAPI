package com.example.kakaobookapi.data.repository

import androidx.lifecycle.LiveData
import com.example.kakaobookapi.data.api.RetrofitInstance.api
import com.example.kakaobookapi.data.db.BookSearchDatabase
import com.example.kakaobookapi.data.model.Book
import com.example.kakaobookapi.data.model.SearchResponse
import retrofit2.Response

class BookSearchRepositoryImpl(
    private val db: BookSearchDatabase
) : BookSearchRepository {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<SearchResponse> {
        return api.searchBooks(query, sort, page, size)
    }

    override suspend fun insertBooks(book: Book) {
        db.bookSearchDao().insertBook(book)
    }

    override suspend fun deleteBooks(book: Book) {
        db.bookSearchDao().deleteBook(book)
    }

    override fun getFavoriteBooks(): LiveData<List<Book>> {
        return db.bookSearchDao().getFavoriteBooks()
    }
}