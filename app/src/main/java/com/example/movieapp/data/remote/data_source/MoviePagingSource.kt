package com.example.movieapp.data.remote.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.remote.entity.mapToModel
import com.example.movieapp.domain.model.Movie
import kotlin.math.max

class MoviePagingSource constructor(private val remoteSource: MovieRemoteSource) :
    PagingSource<Int, Movie>() {

    private val startPage = 0

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val movie = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = movie.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val start = params.key ?: startPage
        val prev = if (start == 0) null else start - 1
        val next = start + 1
        val response = remoteSource.getTopRatedMovies(start)
        val list = if (response.isSuccessful){
            response.body()?.results?.mapToModel() ?: emptyList()
        }else{
            emptyList()
        }
        return LoadResult.Page(list, prev, next)
    }

    private fun ensureValidKey(key: Int) = max(startPage, key)
}