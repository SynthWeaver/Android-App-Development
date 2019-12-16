package nl.terwijn.gamebacklog.ui.add

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*
import nl.terwijn.gamebacklog.R
import nl.terwijn.gamebacklog.model.Game

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

    private fun initViewModel(){

    }

    private fun onSaveClick() {
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val day = etDay.text.toString()
        val month = etMonth.text.toString()
        val year = etYear.text.toString()

        if (title.isNotBlank() && platform.isNotBlank() && day.isNotBlank() && month.isNotBlank() && year.isNotBlank()) {
            val site = Game(
                title,
                platform,
                day,
                month,
                year
            )

            finish()
        } else {
            Toast.makeText(this,"The textfields cannot be empty"
                , Toast.LENGTH_SHORT).show()
        }
    }
}
