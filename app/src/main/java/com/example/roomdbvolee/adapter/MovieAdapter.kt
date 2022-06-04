package com.example.roomdbvolee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbvolee.R
import com.example.roomdbvolee.room.Movie
import kotlinx.android.synthetic.main.adapter_movie.view.*


class MovieAdapter(private val movie: ArrayList<Movie>, private val listener: onAdapterListener) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_movie, parent, false)
        )
    }

    override fun getItemCount() = movie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movie[position]
        holder.view.text_title.text = movie.title
        holder.view.text_title.setOnClickListener{
            listener.onClick(movie)
        }
        holder.view.icon_edit.setOnClickListener{
            listener.onUpdate(movie)
        }
        holder.view.icon_delete.setOnClickListener{
            listener.onDelete(movie)
        }

    }

    class MovieViewHolder(val view: View): RecyclerView.ViewHolder(view)


    fun setData(list: List<Movie>){
        movie.clear()
        movie.addAll(list)
        notifyDataSetChanged()
    }

    interface onAdapterListener{
        fun onClick(movie: Movie)
        fun onUpdate(movie: Movie)
        fun onDelete(movie: Movie)
    }
}



