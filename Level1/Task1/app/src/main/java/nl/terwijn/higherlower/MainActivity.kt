package nl.terwijn.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnLower.setOnClickListener{
            this.buttonPressed()
            this.lowerButtonPressed()
        }

        btnEquals.setOnClickListener{
            this.buttonPressed()
            this.eaqualsButtonPressed()
        }

        btnHigher.setOnClickListener{
            this.buttonPressed()
            this.higherButtonPressed()
        }
    }

    private var lastDiceRoll = 0
    private var diceRoll = 0

    private fun generateRandomNumber(): Int{
        val min = 1
        var max = 6

        return Random().nextInt(++max - min) + min
    }

    private fun buttonPressed(){
        //update last throw
        this.lastDiceRoll = this.diceRoll
        val resultString = getString(R.string.results)
        val result = String.format("$resultString $lastDiceRoll")
        tvResult.text = result

        //roll dice
        this.diceRoll = generateRandomNumber()

        //update image
        var imgResource = -1

        when (this.diceRoll) {
            1 -> imgResource = R.drawable.dice1
            2 -> imgResource = R.drawable.dice2
            3 -> imgResource = R.drawable.dice3
            4 -> imgResource = R.drawable.dice4
            5 -> imgResource = R.drawable.dice5
            6 -> imgResource = R.drawable.dice6
            else -> {
                imgResource = 5
                println("error, imgResource = $imgResource")
            }
        }

        ivDice.setImageResource(imgResource)
    }

    private fun lowerButtonPressed(){
        //inform user about winning or losing.
        if(diceRoll < lastDiceRoll){
            Toast.makeText(this, getString(R.string.winner), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, getString(R.string.loser), Toast.LENGTH_SHORT).show()
        }
    }

    private fun eaqualsButtonPressed(){
        //inform user about winning or losing.
        if(diceRoll == lastDiceRoll){
            Toast.makeText(this, getString(R.string.winner), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, getString(R.string.loser), Toast.LENGTH_SHORT).show()
        }
    }

    private fun higherButtonPressed(){
        //inform user about winning or losing.
        if(diceRoll > lastDiceRoll){
            Toast.makeText(this, getString(R.string.winner), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, getString(R.string.loser), Toast.LENGTH_SHORT).show()
        }
    }




}
