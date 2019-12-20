package nl.terwijn.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.terwijn.gamebacklog.model.Game


@Dao
interface GameDao {
    @Query("SELECT * FROM gameTable")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Query("DELETE FROM gameTable")
    fun nukeTable()
}