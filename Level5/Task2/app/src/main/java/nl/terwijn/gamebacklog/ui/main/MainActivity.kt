package nl.terwijn.gamebacklog.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.games.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import nl.terwijn.gamebacklog.R
import nl.terwijn.gamebacklog.model.Game
import nl.terwijn.gamebacklog.model.MainViewModel
import nl.terwijn.gamebacklog.ui.add.AddGame
import nl.terwijn.gamebacklog.ui.views.MarginItemDecoration


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private var games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)

    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews(){
        btnAdd.setOnClickListener {
            showAddGame()
        }

        rvGames.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvGames.adapter = this.gameAdapter
        rvGames.addItemDecoration(
            MarginItemDecoration()
        )

        createItemTouchHelper().attachToRecyclerView(rvGames)
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.games.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun showAddGame() {
        val intent = Intent(this, AddGame::class.java)
        startActivity(intent)
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
            R.id.action_delete_shopping_list -> {
                deleteAllGames()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllGames() {
        mainViewModel.deleteAllGames()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val gameToDelete = games[position]

                mainViewModel.deleteGame(gameToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    class GameAdapter(private val games: List<Game>) :
        RecyclerView.Adapter<GameAdapter.ViewHolder>() {
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
                val release = "Release:"

                val namesOfTheMonth: Array<String> = arrayOf("null",
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November",
                    "December")

                itemView.tvReleaseDate.text =
                    String.format("%s %s %s %s", release, game.day,
                        namesOfTheMonth[game.month], game.year)
            }
        }
    }
}
