package nl.terwijn.individualassignment.views.feedbackOverview

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nl.terwijn.individualassignment.api.FeedbackRepository
import nl.terwijn.individualassignment.models.Feedback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val feedbackRepository = FeedbackRepository()
    val feedbacks = MutableLiveData<MutableList<Feedback>>()

    fun readFeedbacks() {
        feedbackRepository.readFeedbacks().enqueue(object : Callback<MutableList<Feedback>> {
            override fun onResponse(call: Call<MutableList<Feedback>>, response: Response<MutableList<Feedback>>) {
                if (response.isSuccessful){
                    feedbacks.value = response.body()
                }
                else{
                    toast("An error occurred: ${response.errorBody().toString()}")
                }
            }

            override fun onFailure(call: Call<MutableList<Feedback>>, t: Throwable) {
                toast(t.message.toString())
            }
        })
    }

    fun deleteFeedback(id: Int) {
        feedbackRepository.deleteFeedback(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){
                    toast("Deletion successful!")
                    readFeedbacks()
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