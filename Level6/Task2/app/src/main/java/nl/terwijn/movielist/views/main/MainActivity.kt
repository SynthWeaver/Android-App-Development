package nl.terwijn.movielist.views.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie.view.*
import nl.terwijn.movielist.R
import nl.terwijn.movielist.models.MainViewModel
import nl.terwijn.movielist.models.Movie
import nl.terwijn.movielist.views.movie_details.MovieDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val movies = arrayListOf<Movie>()
    private val movieAdapter =
        MovieAdapter(this, movies)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initViewModel()
        this.initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.getPopularMoviesByYear(2018)

//        // Observe the trivia object.
//        viewModel.trivia.observe(this, Observer {
//            tvTrivia.text = it?.text
//        })
//
//        // Observe the error message.
//        viewModel.error.observe(this, Observer {
//            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//        })
    }

    private fun initViews(){
        btnSubmit.setOnClickListener {
            val data = viewModel.movies
            val dataValus = viewModel.movies.value

            println("yes")
        }

        rvMovies.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvMovies.adapter = this.movieAdapter
    }

    class MovieAdapter(private val mainActivity: MainActivity, private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context
            val view= LayoutInflater.from(context).inflate(R.layout.activity_movie, parent, false)

            view.setOnClickListener {
                val intent = Intent(mainActivity, MovieDetailsActivity::class.java)
                context.startActivity(intent)
            }

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return movies.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.bind(movies[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(movie : Movie) {
                itemView.tvNumber.text = "0"
                itemView.ivMovie.setImageResource(R.mipmap.ic_launcher)
            }
        }
    }
}
