package com.example.movieapp.ui.movie_list

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.domain.model.Movie

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}