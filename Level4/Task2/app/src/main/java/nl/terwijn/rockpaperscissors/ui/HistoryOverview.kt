package nl.terwijn.rockpaperscissors.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_history_overview.*
import kotlinx.android.synthetic.main.history.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.terwijn.rockpaperscissors.R
import nl.terwijn.shoppinglistkotlin.database.ResultRepository
import nl.terwijn.shoppinglistkotlin.model.ResultData


class HistoryOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_overview)

        resultRepository = ResultRepository(this)

        this.initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_history, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete_history_list -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val results = arrayListOf<ResultData>()
    private val resultAdapter = ResultAdapter(results)
    private lateinit var resultRepository: ResultRepository

    private fun initViews(){
        rvResults.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvResults.adapter = this.resultAdapter


        CoroutineScope(Dispatchers.Main).launch {
            val reminders = withContext(Dispatchers.IO) {
                resultRepository.getAllResults()
            }
            this@HistoryOverview.results.clear()
            this@HistoryOverview.results.addAll(reminders)

            resultAdapter.notifyDataSetChanged()
        }
    }

    public class ResultAdapter(private val results: List<ResultData>) :
        RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.history, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return results.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.bind(results[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(resultData : ResultData) {
                itemView.tvResult.text = resultData.result
                itemView.tvDate.text = resultData.date
            }
        }
    }
}