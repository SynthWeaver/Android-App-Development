package nl.terwijn.movielist.views.movie_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_movie_details.*
import nl.terwijn.movielist.R
import nl.terwijn.movielist.models.Movie
import nl.terwijn.movielist.views.main.MainActivity

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        var movie = getMovie()
        this.initViews(movie)
    }

    private fun getMovie(): Movie {
        return intent.getParcelableExtra<Movie>(MainActivity.MOVIE_ID)
    }

    private fun initViews(movie: Movie) {

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        var imageUrl = "https://image.tmdb.org/t/p/w500" + movie.backdrop_path

        //set header image
        Glide.with(this).load(imageUrl).apply(options).into(ivSupportImage)

        imageUrl = "https://image.tmdb.org/t/p/w500" + movie.poster_path

        //set main image
        Glide.with(this).load(imageUrl).apply(options).into(ivMainImage)

        //set name
        tvName.text = movie.title

        //set release date
        tvReleaseDate.text = movie.release_date

        //set star rating
        tvRating.text = movie.popularity.toString()

        //set overview
        tvOverview.text = movie.overview
    }
}
