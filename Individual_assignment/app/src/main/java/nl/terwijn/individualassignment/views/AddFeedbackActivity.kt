package nl.terwijn.individualassignment.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_add_feedback.*
import nl.terwijn.individualassignment.R
import nl.terwijn.individualassignment.models.Feedback
import nl.terwijn.movielist.models.ViewModel

class AddFeedbackActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_feedback)

        this.initViewModel()
        this.initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
    }

    private fun initViews() {
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val feedbackText = etFeedback.text.toString()

            val feedback = Feedback(null, name, feedbackText)

            viewModel.createFeedback(feedback)

            Toast.makeText(this, getString(R.string.feedbackSuccessful), Toast.LENGTH_LONG).show()
        }
    }


}
