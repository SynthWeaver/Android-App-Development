package nl.terwijn.gamebacklog

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val title: String,
    val platform: String,
    val day: String,
    val month: String,
    val year: String
) : Parcelable