package nl.terwijn.reminder

import android.os.Bundle
import android.view.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        this.innitViews()

        btnAddReminder.setOnClickListener{
            this.addReminder()
        }
    }

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    private fun innitViews(){
        rvReminders.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvReminders.adapter = this.reminderAdapter

        rvReminders.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun addReminder(){
        val reminder = etReminder.text.toString();

        if(reminder.isNotBlank()){
            reminders.add(Reminder(reminder))
            reminderAdapter.notifyDataSetChanged()
            etReminder.text?.clear()
        }else{
            Snackbar.make(etReminder, "You must fill in the input field", Snackbar.LENGTH_SHORT).show()
        }
    }




















    data class Reminder(
        var reminder: String
    )

    class ReminderAdapter(private val reminders: List<Reminder>):
        RecyclerView.Adapter<ReminderAdapter.ViewHolder>(){

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1,
                        parent, false)
                )
            }

            override fun getItemCount(): Int {
                return reminders.size;
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.bind(reminders[position])
            }


            inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

                private val tvReminder: TextView = itemView.findViewById(android.R.id.text1)

                fun bind(reminder: Reminder){
                    tvReminder.text = reminder.reminder;
                }
            }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}
