package nl.terwijn.gamebacklog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_game.*
import kotlinx.android.synthetic.main.content_add_game.*

const val EXTRA_SITE = "EXTRA_SITE"

class AddGame : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)
        init()
    }

    private fun init(){
        btnSave.setOnClickListener {
            onSaveClick()
        }
    }

    private fun onSaveClick() {
        val title = etTitle.text.toString()
        val platform = etPlatform.text.toString()
        val day = etDay.text.toString()
        val month = etMonth.text.toString()
        val year = etYear.text.toString()

        if (title.isNotBlank() && platform.isNotBlank() && day.isNotBlank() && month.isNotBlank() && year.isNotBlank()) {
            val site = Game(title, platform, day, month, year)


            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_SITE, site)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this,"The textfields cannot be empty"
                , Toast.LENGTH_SHORT).show()
        }
    }
}
