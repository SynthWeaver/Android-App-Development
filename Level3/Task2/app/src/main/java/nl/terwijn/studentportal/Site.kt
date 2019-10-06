package nl.terwijn.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Site(
    val name: String,
    val url: String
) : Parcelable