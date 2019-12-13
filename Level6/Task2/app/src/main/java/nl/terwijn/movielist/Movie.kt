package nl.terwijn.movielist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val releaseDate: String,
    val mainImage: String,
    val supportImage: String,
    val rating: Double,
    val overview: String
) : Parcelable