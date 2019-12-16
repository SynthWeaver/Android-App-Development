package nl.terwijn.gamebacklog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Game(
    var title: String,
    var platform: String,
    var day: String,
    var month: String,
    var year: String,
    @PrimaryKey var id : Long? = null
) : Parcelable//todo kan weg