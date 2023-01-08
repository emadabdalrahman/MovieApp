package com.example.movieapp.domain.model

data class Genres(
    var id: Int,
    var name: String
) {
    companion object {
        fun all(): Genres = Genres(-1, "All")
    }
}