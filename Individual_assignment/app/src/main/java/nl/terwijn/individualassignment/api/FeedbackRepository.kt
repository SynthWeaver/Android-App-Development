package nl.terwijn.individualassignment.api

import nl.terwijn.individualassignment.models.Feedback
import nl.terwijn.individualassignment.models.User

class FeedbackRepository {

    private val feedbackApi: FeedbackApiService = FeedbackApi.createApi()

    fun createFeedback(feedback: Feedback) = feedbackApi.createFeedback(feedback)

    fun readFeedbacks() = feedbackApi.readFeedbacks()

    fun deleteFeedback(id: Int) = feedbackApi.deleteFeedback(id)

    fun login(user: User) = feedbackApi.login(user)
}