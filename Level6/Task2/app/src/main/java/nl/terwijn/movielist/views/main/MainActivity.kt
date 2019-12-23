package nl.terwijn.movielist.views.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie.view.*
import nl.terwijn.movielist.R
import nl.terwijn.movielist.models.MainViewModel
import nl.terwijn.movielist.models.Movie
import nl.terwijn.movielist.views.movie_details.MovieDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initViewModel()
        this.initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getPopularMoviesByYear(2018)

        // Observe the trivia object.
        viewModel.movies.observe(this, Observer {
            val adapter = MovieAdapter(this, it)
            this.gv_movies.adapter = adapter
        })

        // Observe the error message.
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initViews(){
        btnSubmit.setOnClickListener {
            val year = etYear.text.toString().toInt()
            viewModel.getPopularMoviesByYear(year)
            gv_movies.invalidateViews()
        }
    }

    class MovieAdapter(private var context: Context, var movieList: List<Movie>) : BaseAdapter() {

        override fun getCount(): Int {
            return movieList.size
        }

        override fun getItem(position: Int): Any {
            return movieList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val movieView = inflater.inflate(R.layout.movie, null)

            val movie = this.movieList[position]
            val imageUrl = "https://image.tmdb.org/t/p/w500" + movie.poster_path

            movieView.tvNumber.text = position.toString()

            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)

            Glide.with(context).load(imageUrl).apply(options).into(movieView.ivMovie)

            movieView.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra(MOVIE_ID, movie)
                context.startActivity(intent)
            }

            return movieView
        }
    }

    companion object{
        val MOVIE_ID = "MOVIE_ID"
    }
}
