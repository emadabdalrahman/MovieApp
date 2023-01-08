package com.example.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.ui.BindFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindFragment<FragmentHomeBinding>() {

    private val vm: HomeViewModel by viewModels()
    private lateinit var moviePageAdapter: MoviePageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.genres.observe(viewLifecycleOwner) {
            moviePageAdapter = MoviePageAdapter(childFragmentManager, lifecycle)
            bind.pager.adapter = moviePageAdapter
            moviePageAdapter.setGenres(it)
            TabLayoutMediator(bind.tabs, bind.pager) { tab, position ->
                tab.text = it[position].name
            }.attach()
        }
    }
}