package nl.terwijn.gamebacklog.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.terwijn.gamebacklog.database.GameRepository
import nl.terwijn.gamebacklog.model.Game

class AddGameViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    var error = ""
    var success = false

    fun insertGame(game:Game) {
        if (isNoteValid(game)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game)
                }
                success = true
            }
        }
    }

    private fun isNoteValid(game:Game): Boolean {
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