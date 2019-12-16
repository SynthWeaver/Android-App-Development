package nl.terwijn.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.terwijn.gamebacklog.model.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun insertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Update
    suspend fun updateGame(game: Game)

    @Query("SELECT * FROM Game")
    suspend fun deleteAllGames()//todo
}