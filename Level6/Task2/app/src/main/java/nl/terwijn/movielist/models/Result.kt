package nl.terwijn.movielist.models

import com.google.gson.annotations.SerializedName

data class Result (
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<Movie>,
    @SerializedName("total_results") var total_results: Int,
    @SerializedName("total_pages") var total_pages: Int
)