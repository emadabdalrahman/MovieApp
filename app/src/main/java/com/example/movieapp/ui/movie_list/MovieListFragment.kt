package com.example.movieapp.ui.movie_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.filter
import com.example.movieapp.databinding.FragmentMovieListBinding
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.ui.BindFragment
import com.example.movieapp.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment(private val genres: Genres) : BindFragment<FragmentMovieListBinding>() {

    private val vm: MovieViewModel by viewModels(ownerProducer = { requireActivity() })
    private val adapter: MovieListAdapter by lazy { MovieListAdapter() }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initMovieList()
        lifecycleScope.launch {
            vm.movies.collectLatest { page ->
                adapter.submitData(page.filter {
                    if (genres == Genres.all()) true
                    else it.genre_ids.contains(genres.id)
                })
            }
        }
    }

    private fun initMovieList() {
        adapter.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(it)
            findNavController().navigate(action)
        }
        bind.movieList.adapter = adapter
    }

}