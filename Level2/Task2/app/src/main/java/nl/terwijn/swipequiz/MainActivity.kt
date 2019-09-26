package nl.terwijn.swipequiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
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
        this.buildRecyclerView()
    }

    private fun buildRecyclerView() {
        val recyclerView = SimpleRecyclerView(rvQuestions)

        recyclerView.addRecyclerViewRow(getString(R.string.question1))
        recyclerView.addRecyclerViewRow(getString(R.string.question2))
        recyclerView.addRecyclerViewRow(getString(R.string.question3))

        createItemTouchHelperLeft(recyclerView).attachToRecyclerView(rvQuestions)
        createItemTouchHelperRight(recyclerView).attachToRecyclerView(rvQuestions)

        rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun createItemTouchHelperLeft(recyclerView: SimpleRecyclerView): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                println("left" + position)
                recyclerView.getAdapter().notifyItemChanged(viewHolder.adapterPosition)
            }

        }

        return ItemTouchHelper(callback)
    }

    private fun createItemTouchHelperRight(recyclerView: SimpleRecyclerView): ItemTouchHelper {
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
                println("right")
                recyclerView.getAdapter().notifyItemChanged(viewHolder.adapterPosition)
            }

        }

        return ItemTouchHelper(callback)
    }
}
