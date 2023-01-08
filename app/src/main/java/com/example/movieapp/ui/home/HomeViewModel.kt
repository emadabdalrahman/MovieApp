package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.model.Genres
import com.example.movieapp.domain.use_case.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase) : ViewModel() {

    private val _genres: MutableLiveData<List<Genres>> = MutableLiveData()
    val genres: LiveData<List<Genres>> = _genres

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _genres.postValue(moviesUseCase.getGenres())
        }
    }

}