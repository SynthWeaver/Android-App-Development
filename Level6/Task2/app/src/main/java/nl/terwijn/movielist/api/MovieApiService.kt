package nl.terwijn.movielist.api

import nl.terwijn.movielist.models.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService  {

    @GET("/3/discover/movie?api_key=1bdef403d5a88c98e0767f5565e69ce4&language=en-US&sort_by=vote_average.desc&include_adult=false&include_video=false&page=1&vote_count.gte=1000")
    fun getPopularMovies(@Query("primary_release_year") year: Int): Call<Result>
}
