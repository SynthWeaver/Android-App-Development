package nl.terwijn.individualassignment.models

import com.google.gson.annotations.SerializedName

data class Feedback(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("feedback") val feedback: String
)