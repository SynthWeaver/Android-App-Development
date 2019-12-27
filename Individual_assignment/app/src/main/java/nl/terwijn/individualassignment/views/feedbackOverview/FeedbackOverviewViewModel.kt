package nl.terwijn.individualassignment.views.feedbackOverview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nl.terwijn.individualassignment.api.FeedbackRepository
import nl.terwijn.individualassignment.models.Feedback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val feedbackRepository = FeedbackRepository()
    val feedbacks = MutableLiveData<List<Feedback>>()
    val error = MutableLiveData<String>()

    fun readFeedbacks() {
        feedbackRepository.readFeedbacks().enqueue(object : Callback<List<Feedback>> {
            override fun onResponse(call: Call<List<Feedback>>, response: Response<List<Feedback>>) {
                if (response.isSuccessful){
                    feedbacks.value = response.body()
                }
                else{
                    error.value = "An error occurred: ${response.errorBody().toString()}"
                }
            }

            override fun onFailure(call: Call<List<Feedback>>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}