package com.example.movieapp.data.remote.entity

import com.google.gson.annotations.SerializedName

data class GenresResponseBody(
    @SerializedName("genres") val genres :List<GenresRemote>
)