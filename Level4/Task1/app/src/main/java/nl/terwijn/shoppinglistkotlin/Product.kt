package nl.terwijn.shoppinglistkotlin

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "product_table")
data class Product(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int
)