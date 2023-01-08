package com.example.movieapp.data.remote.entity

import com.example.movieapp.domain.model.Genres
import com.google.gson.annotations.SerializedName

data class GenresRemote(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)

fun List<GenresRemote>.mapToModel(): List<Genres> = map { it.mapToModel() }

fun GenresRemote.mapToModel(): Genres {
    return Genres(id, name)
}