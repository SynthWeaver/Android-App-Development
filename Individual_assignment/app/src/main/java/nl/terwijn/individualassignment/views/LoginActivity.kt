package nl.terwijn.individualassignment.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import nl.terwijn.individualassignment.R
import nl.terwijn.individualassignment.models.User
import nl.terwijn.movielist.models.ViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.initViewModel()
        this.initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
    }

    private fun initViews() {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val inputUser = User(email, password)

            if(viewModel.login(inputUser)){
                startFeedbackOverview()
            }else{
                Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startFeedbackOverview(){
        val intent = Intent(this, FeedbackOverviewActivity::class.java)
        startActivity(intent)
    }
}
