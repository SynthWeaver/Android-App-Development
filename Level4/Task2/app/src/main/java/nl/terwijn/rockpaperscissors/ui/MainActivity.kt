package nl.terwijn.rockpaperscissors.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.terwijn.rockpaperscissors.R
import nl.terwijn.shoppinglistkotlin.database.ResultRepository
import nl.terwijn.shoppinglistkotlin.model.ResultData

class MainActivity : AppCompatActivity() {

    private lateinit var resultRepository: ResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultRepository = ResultRepository(this)

        loadViews()
    }

    private fun loadViews() {
        ibRock.setOnClickListener {
            playGame(Input.ROCK)
        }

        ibPaper.setOnClickListener {
            playGame(Input.PAPER)
        }

        ibScissors.setOnClickListener {
            playGame(Input.SCISSORS)
        }

        updateHistory()
    }

    private fun playGame(playerInput: String) {
        //get computer input
        val computerInput = Computer().getAnswer()

        //checks who wins
        val result = Result().getWinner(playerInput, computerInput)

        //show result
        showResults(playerInput, computerInput, result)

        //save
        addResultData(playerInput, computerInput, result)
    }

    private fun addResultData(playerInput: String,  computerInput: String, result: String
    ) {
        if (result.isNotBlank()) {
            mainScope.launch {
                val resultData = ResultData(
                    playerInput,
                    computerInput,
                    result
                )

                withContext(Dispatchers.IO) {
                    resultRepository.insertResult(resultData)
                }

                updateHistory()
            }
        }
    }

    private fun showResults(playerInput: String, computerInput: String, result: String) {
        when (playerInput) {
            Input.ROCK -> ivYou.setImageResource(R.drawable.rock)
            Input.PAPER -> ivYou.setImageResource(R.drawable.paper)
            Input.SCISSORS -> ivYou.setImageResource(R.drawable.scissors)
        }

        when (computerInput) {
            Input.ROCK -> ivComputer.setImageResource(R.drawable.rock)
            Input.PAPER -> ivComputer.setImageResource(R.drawable.paper)
            Input.SCISSORS -> ivComputer.setImageResource(
                R.drawable.scissors
            )
        }

        tvResult.text = result
    }

    private fun updateHistory() {
        CoroutineScope(Dispatchers.Main).launch {
            var wins = 0
            var draws = 0
            var loses = 0

            val reminders = withContext(Dispatchers.Main) {
                resultRepository.getAllResults()
            }

            for (reminder in reminders){
                when {
                    reminder.result == Result.WIN -> wins++
                    reminder.result == Result.DRAW -> draws++
                    else -> loses++
                }
            }

            val resultString = String.format("%s %s %s %s %s %s",
                getString(R.string.win),
                wins,
                getString(R.string.draw),
                draws,
                getString(R.string.lose),
                loses
            )

            tvStats.text = resultString
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
            R.id.action_open_history -> {
                openHistoryActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openHistoryActivity() {
        val intent = Intent(this, HistoryOverview::class.java)
        startActivityForResult (intent, 0)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        updateHistory()
    }
}