package com.example.movieapp.ui.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.domain.model.Movie


class MovieListAdapter : PagingDataAdapter<Movie, MovieListAdapter.VH>(MovieDiffUtil()) {

    private var onItemClickListener: (Movie) -> Unit = {}

    fun setOnClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val bind = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(bind, onItemClickListener)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        if (item != null) holder.updateView(item)

    }

    inner class VH(private val bind: MovieItemBinding, listener: (Movie) -> Unit) :
        ViewHolder(bind.root) {

        init {
            bind.root.setOnClickListener {
                val item = getItem(layoutPosition)
                if (item != null) listener(item)
            }
        }

        fun updateView(movie: Movie) {
            bind.name.text = movie.original_title
            bind.year.text = movie.release_date
            if (!movie.poster_path.isNullOrBlank()) {
                Glide.with(bind.image)
                    .load(BuildConfig.BASE_IMAGE_URL + movie.poster_path)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(bind.image)
            }
        }

    }
}