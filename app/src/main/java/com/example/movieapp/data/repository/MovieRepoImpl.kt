package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.remote.data_source.MoviePagingSource
import com.example.movieapp.data.remote.data_source.MovieRemoteSource
import com.example.movieapp.data.remote.entity.mapToModel
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val remoteSource: MovieRemoteSource
) : MovieRepo {

    override  fun getMoviePagingSource(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(remoteSource) }
        ).flow
    }

    override suspend fun getGenres(): List<Genres> {
        val response = remoteSource.getGenres()
        return if (response.isSuccessful) {
            response.body()?.genres?.mapToModel() ?: emptyList()
        } else {
            emptyList()
        }
    }

}