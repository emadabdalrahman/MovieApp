package com.example.movieapp.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSecondBinding
import com.example.movieapp.ui.BindFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BindFragment<FragmentSecondBinding>() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        NavigationUI.setupWithNavController(bind.toolbar,findNavController())

        val movie = args.movie

        bind.toolbar.title = movie.title
        bind.title.text = movie.title
        bind.date.text = movie.release_date
        bind.rating.text = movie.vote_average.toString()
        bind.details.text = movie.overview

        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_URL + movie.backdrop_path)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(bind.backDrop)

        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE_URL + movie.poster_path)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(bind.poster)
    }
}