package com.example.movieapp.data.repository

import androidx.paging.PagingData
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    suspend fun getGenres(): List<Genres>
     fun getMoviePagingSource(): Flow<PagingData<Movie>>
}
