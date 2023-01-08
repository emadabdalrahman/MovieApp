package com.example.movieapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.ui.movie_list.MovieListFragment

class MoviePageAdapter(fm :FragmentManager,lifecycle: Lifecycle) : FragmentStateAdapter(fm,lifecycle) {

    private val genres: ArrayList<Genres> = arrayListOf()

    fun setGenres(genres: List<Genres>){
        this.genres.clear()
        this.genres.addAll(genres)
    }

    override fun getItemCount(): Int {
        return genres.count()
    }

    override fun createFragment(position: Int): Fragment {
        return MovieListFragment(genres[position])
    }



}