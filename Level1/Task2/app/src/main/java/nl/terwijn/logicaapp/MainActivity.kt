package nl.terwijn.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit.setOnClickListener{
            onSubmitButtonClick()
        }
    }

    private fun onSubmitButtonClick(){
        val input1 = ivInput1.text.toString().toUpperCase()
        val input2 = ivInput2.text.toString().toUpperCase()
        val input3 = ivInput3.text.toString().toUpperCase()
        val input4 = ivInput4.text.toString().toUpperCase()

        if( input1 == "T" &&
            input2 == "F" &&
            input3 == "F" &&
            input4 == "F") {
                Toast.makeText(this, getText(R.string.correct),Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, getText(R.string.incorrect),Toast.LENGTH_SHORT).show()
        }

        ivInput1.setText("")
        ivInput2.setText("")
        ivInput3.setText("")
        ivInput4.setText("")
    }
}
