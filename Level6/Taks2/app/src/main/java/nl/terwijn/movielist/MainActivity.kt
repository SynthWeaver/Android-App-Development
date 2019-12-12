package nl.terwijn.movielist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie.view.*

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(this, movies)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()
    }

    private fun init(){
        val loremIpsum: String = getString(R.string.lorem_ipsum)
        movies.add(Movie("test", "2 jan 1995", "url", "url2", 8.1, loremIpsum))
        movies.add(Movie("test", "2 jan 1995", "url", "url2", 8.1, loremIpsum))
        movies.add(Movie("test", "2 jan 1995", "url", "url2", 8.1, loremIpsum))
        movies.add(Movie("test", "2 jan 1995", "url", "url2", 8.1, loremIpsum))

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

        private fun openUrl(url: String) {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
    }
}
