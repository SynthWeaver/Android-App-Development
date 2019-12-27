package nl.terwijn.individualassignment.views.login

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import nl.terwijn.individualassignment.api.FeedbackRepository
import nl.terwijn.individualassignment.models.User
import nl.terwijn.individualassignment.views.feedbackOverview.FeedbackOverviewActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val feedbackRepository = FeedbackRepository()

    fun login(user: User){
        feedbackRepository.login(user).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful){
                    if(response.body()!!){
                        toast("Login successful!")
                        startFeedbackOverview()
                    }else{
                        toast("Email or password incorrect, please try again.")
                    }
                }
                else{
                    toast("An error occurred: ${response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                toast(t.message.toString())
            }

        })
    }

    private fun startFeedbackOverview(){
        val intent = Intent(getApplication(), FeedbackOverviewActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
        startActivity(getApplication(), intent, null)
    }

    private fun toast(text: String){
        Toast.makeText(getApplication(), text, Toast.LENGTH_LONG).show()
    }
}