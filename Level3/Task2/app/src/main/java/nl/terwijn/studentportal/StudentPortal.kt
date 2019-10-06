package nl.terwijn.studentportal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_student_portal.*

const val EXTRA_SITE = "EXTRA_SITE"

class StudentPortal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_portal)
        init()
    }

    private fun init(){
        btnSave.setOnClickListener {
            onSaveClick()
        }
    }

    private fun onSaveClick() {
        val name = etName.text.toString()
        val url = etUrl.text.toString()

        if (name.isNotBlank() && url.isNotBlank()) {
            val site = Site(name, url)


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
