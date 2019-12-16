package nl.terwijn.individualassignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        btnUser.setOnClickListener { startAddFeedback() }
        btnAdmin.setOnClickListener { startLogin() }
    }

    private fun startAddFeedback(){
        val intent = Intent(this, AddFeedbackActivity::class.java)
        startActivity(intent)
    }

    private fun startLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
