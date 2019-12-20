package nl.terwijn.gamebacklog.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import nl.terwijn.gamebacklog.R
import nl.terwijn.gamebacklog.model.Game
import nl.terwijn.gamebacklog.model.MainViewModel

class AddGame : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

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
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private fun onSaveClick() {
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val day = etDay.text.toString()
        val month = etMonth.text.toString()
        val year = etYear.text.toString()

        val game = Game(title, platform, day, month, year)

        mainViewModel.insertGame(game)

        if(!mainViewModel.success){
            Toast.makeText(this,mainViewModel.error
                , Toast.LENGTH_SHORT).show()

            return
        }

        finish()
    }
}
