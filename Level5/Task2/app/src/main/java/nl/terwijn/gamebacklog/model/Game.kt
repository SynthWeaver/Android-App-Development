package nl.terwijn.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gameTable")
data class Game(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "platform")
    var platform: String,

    @ColumnInfo(name = "day")
    var day: Int,

    @ColumnInfo(name = "month")
    var month: Int,

    @ColumnInfo(name = "year")
    var year: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable