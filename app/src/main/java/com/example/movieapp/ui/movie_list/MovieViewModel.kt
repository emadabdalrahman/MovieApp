package com.example.movieapp.ui.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.use_case.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Suppress("DeferredResultUnused")
@HiltViewModel
class MovieViewModel @Inject constructor(moviesUseCase: MoviesUseCase) : ViewModel() {

    val movies: Flow<PagingData<Movie>> = moviesUseCase.getMoviePagingSource().cachedIn(viewModelScope)
}