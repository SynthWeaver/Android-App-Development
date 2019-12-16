package nl.terwijn.gamebacklog.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.games.view.*
import nl.terwijn.gamebacklog.R
import nl.terwijn.gamebacklog.model.Game
import nl.terwijn.gamebacklog.ui.add.AddGame

class MainActivity : AppCompatActivity() {

    private val sites = arrayListOf<Game>()
    private val siteAdapter =
        SiteAdapter(sites)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        init()
    }

    private fun init(){
        btnAdd.setOnClickListener {
            onAddClick()
        }

        rvGames.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvGames.adapter = this.siteAdapter
    }

    private fun onAddClick() {
        val intent = Intent(this, AddGame::class.java)
        startActivity(intent)
    }

    public class SiteAdapter(private val games: List<Game>) :
        RecyclerView.Adapter<SiteAdapter.ViewHolder>() {
        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.games, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return games.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.bind(games[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(game : Game) {
                itemView.tvTitle.text = game.title
                itemView.tvPlatform.text = game.platform
                itemView.tvReleaseDate.text = String.format("%s %s %s", game.day, game.month, game.year)
            }
        }
    }
}
