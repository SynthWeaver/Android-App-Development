package nl.terwijn.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerView constructor( recyclerView : RecyclerView) {

    private var recyclerViewRows = arrayListOf<RecyclerViewRow>()
    private val adapter = Adapter(recyclerViewRows)

    //constructor
    init {
        recyclerView.adapter = this.adapter
    }

    public fun addRecyclerViewRow(text : String){
        val recyclerViewRow = RecyclerViewRow(text)
        recyclerViewRows.add(recyclerViewRow)
        adapter.notifyDataSetChanged()
    }

    public fun removeRecyclerViewRow(index : Int){
        recyclerViewRows.removeAt(index)
    }

    private data class RecyclerViewRow(
        var text: String
    )

    private class Adapter(private val RecyclerViewRow: List<RecyclerViewRow>):
        RecyclerView.Adapter<Adapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,
                    parent, false)
            )
        }

        override fun getItemCount(): Int {
            return RecyclerViewRow.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(RecyclerViewRow[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

            private val textView: TextView = itemView.findViewById(android.R.id.text1)

            fun bind(RecyclerViewRow: RecyclerViewRow){
                textView.text = RecyclerViewRow.text;
            }
        }
    }
}