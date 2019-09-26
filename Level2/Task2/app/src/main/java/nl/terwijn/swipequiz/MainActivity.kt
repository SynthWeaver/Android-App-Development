package nl.terwijn.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionsAdapter = QuestionsAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.innit()
    }

    private fun innit(){
        this.fillRecyclerView()
        this.innitViews()
    }

    private fun fillRecyclerView() {
        questions.add(Question(getString(R.string.question1)))
        questions.add(Question(getString(R.string.question2)))
        questions.add(Question(getString(R.string.question3)))

        questionsAdapter.notifyDataSetChanged()
    }

    private fun innitViews(){
        rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = this.questionsAdapter

        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //createItemTouchHelper().attachToRecyclerView(rvReminders)
    }

    data class Question(
        var question: String
    )

    class QuestionsAdapter(private val questions: List<Question>):
        RecyclerView.Adapter<QuestionsAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,
                    parent, false)
            )
        }

        override fun getItemCount(): Int {
            return questions.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(questions[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            private val textView: TextView = itemView.findViewById(android.R.id.text1)

            fun bind(question: Question){
                textView.text = question.question;
            }
        }
    }
}
