package nl.terwijn.individualassignment.views.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import nl.terwijn.individualassignment.R
import nl.terwijn.individualassignment.models.User

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.initViewModel()
        this.initViews()
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    private fun initViews() {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val inputUser = User(email, password)

            loginViewModel.login(inputUser)

//            if(true){//viewModel.login(inputUser)){
//                startFeedbackOverview()
//            }else{
//                Toast.makeText(this, getString(R.string.errorLogin), Toast.LENGTH_LONG).show()
//            }
        }
    }
}
