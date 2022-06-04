package com.example.roomdbvolee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roomdbvolee.room.Constant
import com.example.roomdbvolee.room.Movie
import com.example.roomdbvolee.room.MovieDB
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    val db by lazy { MovieDB(this) }
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        setupView()
        setupListener()

    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType) {
            Constant.TYPE_CREATE -> {
                button_update.visibility = View.GONE

            }
            Constant.TYPE_READ -> {
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getMovie()
            }
            Constant.TYPE_UPDATE -> {
                button_save.visibility = View.GONE
                getMovie()
            }
        }
    }

    fun setupListener(){
        button_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.MovieDao().addMovie(
                    Movie (0, edit_title.text.toString(), edit_movie.text.toString() )
                )
                finish()
            }
        }
        button_update.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                db.MovieDao().updateMovie(
                    Movie (movieId, edit_title.text.toString(), edit_movie.text.toString() )
                )
                finish()
            }
        }

    }

    fun getMovie() {
        movieId = intent.getIntExtra("intent_id", 0)
        CoroutineScope(Dispatchers.IO).launch{
            val movies = db.MovieDao().getMovie(movieId)[0]
            edit_title.setText(movies.title)
            edit_movie.setText(movies.movie)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}