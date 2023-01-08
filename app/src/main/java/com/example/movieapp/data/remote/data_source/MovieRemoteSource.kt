package com.example.movieapp.data.remote.data_source

import com.example.movieapp.data.remote.entity.GenresResponseBody
import com.example.movieapp.data.remote.entity.MovieResponseBody
import retrofit2.Response

interface MovieRemoteSource {

    suspend fun getGenres(): Response<GenresResponseBody>

    suspend fun getTopRatedMovies(page: Int): Response<MovieResponseBody>
}