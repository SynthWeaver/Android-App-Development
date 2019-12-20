package nl.terwijn.gamebacklog.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.terwijn.gamebacklog.database.GameRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val gameRepository = GameRepository(application.applicationContext)
    val games = gameRepository.getAllGames()

    private val mainScope = CoroutineScope(Dispatchers.Main)

    var error = ""
    var success = false

    fun deleteGame(game: Game) {
        ioScope.launch {
            gameRepository.deleteGame(game)
        }
    }

    fun deleteAllGames() {
        ioScope.launch {
            gameRepository.deleteAllGames()
        }
    }

    fun insertGame(game: Game) {

        success = isNoteValid(game)

        if (success) {
            ioScope.launch {
                gameRepository.insertGame(game)
            }
        }
    }

    private fun isNoteValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error = "Title must not be empty"
                false
            }
            game.platform.isBlank() -> {
                error = "Title must not be empty"
                false
            }
            game.day.isBlank() -> {
                error = "Title must not be empty"
                false
            }
            game.month.isBlank() -> {
                error = "Title must not be empty"
                false
            }
            game.year.isBlank() -> {
                error = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}