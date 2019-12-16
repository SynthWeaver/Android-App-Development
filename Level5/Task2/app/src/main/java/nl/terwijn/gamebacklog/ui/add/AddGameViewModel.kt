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

    val game = MutableLiveData<Game?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    fun instertGame() {
        if (isNoteValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game.value!!)
                }
                success.value = true
            }
        }
    }

    private fun isNoteValid(): Boolean {
        return when {
            game.value == null -> {
                error.value = "Note must not be null"
                false
            }
            game.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }
}