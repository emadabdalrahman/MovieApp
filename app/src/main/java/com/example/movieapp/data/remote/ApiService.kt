package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.entity.GenresResponseBody
import com.example.movieapp.data.remote.entity.MovieResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("genre/movie/list")
    suspend fun getGenres(): Response<GenresResponseBody>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page :Int): Response<MovieResponseBody>
}