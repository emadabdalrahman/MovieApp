package com.example.movieapp.data.remote.entity

import com.google.gson.annotations.SerializedName

data class MovieResponseBody(
    @SerializedName("page") val page :Int,
    @SerializedName("results") val results :List<MovieRemote>,
    @SerializedName("total_pages") val totalPages :Int,
    @SerializedName("total_results") val totalResults :Int,
)