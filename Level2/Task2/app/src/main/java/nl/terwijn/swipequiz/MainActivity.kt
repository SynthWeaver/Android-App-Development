package nl.terwijn.swipequiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()
    }

    private fun init(){
        this.fillRecyclerView()
        this.innitViews()
    }

    private fun fillRecyclerView() {
        val recyclerView = MyRecyclerView(rvQuestions)

        recyclerView.addRecyclerViewRow(getString(R.string.question1))
        recyclerView.addRecyclerViewRow(getString(R.string.question2))
        recyclerView.addRecyclerViewRow(getString(R.string.question3))
    }

    private fun innitViews(){
        rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        //createItemTouchHelper().attachToRecyclerView(rvReminders)
    }
}
