package nl.terwijn.movielist.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_count") var vote_count: Int,
    @SerializedName("video") var video: Boolean,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("id") var id: Int,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdrop_path: String,
    @SerializedName("original_language") var original_language: String,
    @SerializedName("original_title") var original_title: String,
    @SerializedName("genre_ids") var genre_ids: List<Int>,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var vote_average: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var release_date: String
):Parcelable