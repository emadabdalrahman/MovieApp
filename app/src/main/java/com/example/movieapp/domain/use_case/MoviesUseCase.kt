package com.example.movieapp.domain.use_case

import androidx.paging.PagingData
import com.example.movieapp.data.repository.MovieRepo
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val movieRepo: MovieRepo) {

    suspend fun getGenres(): List<Genres> {
        return arrayListOf<Genres>().apply {
            add(Genres.all())
            addAll(movieRepo.getGenres())
        }
    }

     fun getMoviePagingSource(): Flow<PagingData<Movie>> {
        return movieRepo.getMoviePagingSource()
    }
}