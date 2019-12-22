package nl.terwijn.movielist.api

import nl.terwijn.movielist.models.Result
import retrofit2.Call
import retrofit2.http.GET

public interface MovieApiService  {
    // The GET method needed to retrieve a random number trivia.
    @GET("/3/discover/movie?api_key=1bdef403d5a88c98e0767f5565e69ce4&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&year=2018")
    fun getPopularMovies(): Call<Result>
}
