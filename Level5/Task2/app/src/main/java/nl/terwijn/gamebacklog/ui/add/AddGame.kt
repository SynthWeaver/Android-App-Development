package nl.terwijn.gamebacklog.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import nl.terwijn.gamebacklog.R
import nl.terwijn.gamebacklog.model.Game
import androidx.lifecycle.Observer

class AddGame : AppCompatActivity() {

    private lateinit var addGameViewModel: AddGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        initViews()
        initViewModel()
    }

    private fun initViews(){
        btnSave.setOnClickListener {
            onSaveClick()
        }
    }

    private fun initViewModel() {
        addGameViewModel = ViewModelProviders.of(this).get(AddGameViewModel::class.java)
    }

    private fun onSaveClick() {
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val day = etDay.text.toString()
        val month = etMonth.text.toString()
        val year = etYear.text.toString()

        val game = Game(title, platform, day, month, year)
        addGameViewModel.insertGame(game)

        if(!addGameViewModel.success){
            Toast.makeText(this,addGameViewModel.error
                , Toast.LENGTH_SHORT).show()

            return
        }

        finish()
    }
}
