package nl.terwijn.individualassignment.api

import nl.terwijn.individualassignment.models.Feedback
import nl.terwijn.individualassignment.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

public interface FeedbackApiService  {

    @POST("/create/feedback")
    fun createFeedback(@Body feedback: Feedback): Call<Void>

    @GET("/read/feedbacks")
    fun readFeedbacks(): Call<List<Feedback>>

    @POST("/login")
    fun login(@Body user: User): Call<Boolean>
}
