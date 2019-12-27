package nl.terwijn.individualassignment.views.addFeedback

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import nl.terwijn.individualassignment.api.FeedbackRepository
import nl.terwijn.individualassignment.models.Feedback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFeedbackViewModel(application: Application) : AndroidViewModel(application) {

    private val feedbackRepository = FeedbackRepository()

    fun createFeedback(feedback: Feedback){
        feedbackRepository.createFeedback(feedback).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    toast("Feedback has been submitted")
                }
                else{
                    toast("An error occurred: ${response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                toast(t.message.toString())
            }

        })
    }

    private fun toast(text: String){
        Toast.makeText(getApplication(), text, Toast.LENGTH_LONG).show()
    }
}