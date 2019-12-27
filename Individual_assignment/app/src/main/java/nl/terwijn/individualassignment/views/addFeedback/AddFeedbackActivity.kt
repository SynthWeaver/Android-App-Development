package nl.terwijn.individualassignment.views.addFeedback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_feedback.*
import nl.terwijn.individualassignment.R
import nl.terwijn.individualassignment.models.Feedback

class AddFeedbackActivity : AppCompatActivity() {

    private lateinit var addFeedbackViewModel: AddFeedbackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_feedback)

        this.initViewModel()
        this.initViews()
    }

    private fun initViewModel() {
        addFeedbackViewModel = ViewModelProviders.of(this).get(AddFeedbackViewModel::class.java)
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val feedbackText = etFeedback.text.toString()

            val feedback = Feedback(null, name, feedbackText)

            addFeedbackViewModel.createFeedback(feedback)
        }
    }


}
