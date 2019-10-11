package nl.terwijn.shoppinglistkotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import nl.terwijn.shoppinglistkotlin.model.ResultData

@Dao
interface ResultDao {
    @Query("SELECT * FROM result_table")
    suspend fun getAllProducts(): List<ResultData>

    @Insert
    suspend fun insertResult(result: ResultData)

    @Query("DELETE FROM result_table")
    suspend fun deleteAllResults()
}