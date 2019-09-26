package nl.terwijn.swipequiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val recyclerView = SimpleRecyclerView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.init()
    }

    private fun init(){
        this.buildRecyclerView()
    }

    private fun buildRecyclerView() {
        recyclerView.setAdapter(rvQuestions)

        recyclerView.addRecyclerViewRow(getString(R.string.question1))
        recyclerView.addRecyclerViewRow(getString(R.string.question2))
        recyclerView.addRecyclerViewRow(getString(R.string.question3))
        recyclerView.addRecyclerViewRow(getString(R.string.question4))
        recyclerView.addRecyclerViewRow(getString(R.string.question5))
        recyclerView.addRecyclerViewRow(getString(R.string.question6))

        createItemTouchHelperLeft().attachToRecyclerView(rvQuestions)
        createItemTouchHelperRight().attachToRecyclerView(rvQuestions)

        rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun swiped(position: Int, answer: Boolean) {
        val question = recyclerView.getRecyclerRowText(position)

        if(question == getString(R.string.question1)){
            if(answer){
                Snackbar.make(rvQuestions,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(rvQuestions,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
            }
        }
        if(question == getString(R.string.question2)){
            if(answer){
                Snackbar.make(rvQuestions,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(rvQuestions,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
            }
        }
        if(question == getString(R.string.question3)){
            if(answer){
                Snackbar.make(rvQuestions,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(rvQuestions,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
            }
        }
        if(question == getString(R.string.question4)){
            if(answer){
                Snackbar.make(rvQuestions,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(rvQuestions,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
            }
        }
        if(question == getString(R.string.question5)){
            if(answer){
                Snackbar.make(rvQuestions,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(rvQuestions,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
            }
        }
        if(question == getString(R.string.question6)){
            if(answer){
                Snackbar.make(rvQuestions,getString(R.string.correct), Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(rvQuestions,getString(R.string.incorrect), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun createItemTouchHelperLeft(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                println("yes2")
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                swiped(position, false)
                recyclerView.getAdapter().notifyItemChanged(viewHolder.adapterPosition)
            }

        }

        return ItemTouchHelper(callback)
    }

    private fun createItemTouchHelperRight(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                swiped(position, true)
                recyclerView.getAdapter().notifyItemChanged(viewHolder.adapterPosition)
            }
        }

        return ItemTouchHelper(callback)
    }
}
