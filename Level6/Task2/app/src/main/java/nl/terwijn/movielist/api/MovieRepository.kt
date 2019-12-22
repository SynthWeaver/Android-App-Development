package nl.terwijn.movielist.api

class MovieRepository {

    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getPopularMovies(year: Int) = movieApi.getPopularMovies()
}