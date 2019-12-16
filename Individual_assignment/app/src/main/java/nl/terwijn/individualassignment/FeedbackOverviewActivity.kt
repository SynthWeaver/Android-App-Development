package nl.terwijn.individualassignment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_feedback_overview.*
import kotlinx.android.synthetic.main.feedback.view.*

class FeedbackOverviewActivity : AppCompatActivity() {

    private val feedbacks = arrayListOf<Feedback>()
    private val feedbackAdapter = FeedbackAdapter(feedbacks)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_overview)

        init()
    }

    private fun init(){
        feedbacks.add(Feedback("Tim Terwijn", "Nice app, but there are a lot of things you could still improve!"))
        feedbacks.add(Feedback("Rik Terwijn", "I hate this app, It is so terable"))

        rvFeedbacks.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvFeedbacks.adapter = this.feedbackAdapter
    }

    class FeedbackAdapter(private val feedbacks: List<Feedback>) : RecyclerView.Adapter<FeedbackAdapter.ViewHolder>() {
        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.feedback, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return feedbacks.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.bind(feedbacks[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(feedback : Feedback) {
                itemView.tvName.text = feedback.name
                itemView.tvFeedback.text = feedback.feedback
            }
        }

        private fun openUrl(url: String) {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
    }
}
