package nl.terwijn.individualassignment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feedback(
    val name: String,
    val feedback: String
) : Parcelable