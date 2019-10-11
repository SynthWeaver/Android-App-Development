package nl.terwijn.shoppinglistkotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import nl.terwijn.rockpaperscissors.ui.DateTime

@Entity(tableName = "result_table")
data class ResultData(
    @ColumnInfo(name = "result")
    var result: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "date")
    var date: String = DateTime.now()
}