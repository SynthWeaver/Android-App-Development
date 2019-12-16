package nl.terwijn.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Game(
    val title: String,
    val platform: String,
    val day: String,
    val month: String,
    val year: String,
    @PrimaryKey var id : Long? = null
) : Parcelable