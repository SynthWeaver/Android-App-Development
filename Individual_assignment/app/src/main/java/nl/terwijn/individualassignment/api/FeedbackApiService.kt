package nl.terwijn.individualassignment.api

import nl.terwijn.individualassignment.models.Feedback
import nl.terwijn.individualassignment.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

public interface FeedbackApiService  {

    @POST("/create/feedback")
    fun createFeedback(@Body feedback: Feedback): Call<Void>

    @GET("/read/feedbacks")
    fun readFeedbacks(): Call<MutableList<Feedback>>

    @POST("/delete/feedback/{id}")
    fun deleteFeedback(@Path("id") id: Int): Call<Void>

    @POST("/login")
    fun login(@Body user: User): Call<Boolean>
}
