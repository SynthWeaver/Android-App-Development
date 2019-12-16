package nl.terwijn.gamebacklog.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import nl.terwijn.gamebacklog.database.GameRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    val games = gameRepository.getAllGames()

    fun deleteGame() {
        //todo
    }

    fun deleteAllGames() {
        //todo
    }
}