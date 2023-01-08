package com.example.movieapp.data.remote.data_source

import com.example.movieapp.data.remote.ApiService
import com.example.movieapp.data.remote.entity.GenresResponseBody
import com.example.movieapp.data.remote.entity.MovieResponseBody
import retrofit2.Response
import javax.inject.Inject


class MovieRemoteSourceImpl @Inject constructor(private val client: ApiService) :
    MovieRemoteSource {

    override suspend fun getGenres(): Response<GenresResponseBody> {
        return client.getGenres()
    }

    override suspend fun getTopRatedMovies(page: Int): Response<MovieResponseBody> {
        return client.getTopRatedMovies(page)
    }

}