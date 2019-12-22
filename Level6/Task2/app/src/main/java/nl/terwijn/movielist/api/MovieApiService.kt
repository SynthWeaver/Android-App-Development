package nl.terwijn.movielist.api

import retrofit2.Call
import nl.terwijn.movielist.models.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface MovieApiService  {
    // The GET method needed to retrieve a random number trivia.
    @GET("/3/discover/movie?api_key=1bdef403d5a88c98e0767f5565e69ce4&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&year=2018")
    fun getPopularMovies(): Call<List<Movie>>

//    @GET("/3/discover/movie?api_key=1bdef403d5a88c98e0767f5565e69ce4&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
//    fun getPopularMovies(@Query("year") year: Int): Call<Movie>
}
