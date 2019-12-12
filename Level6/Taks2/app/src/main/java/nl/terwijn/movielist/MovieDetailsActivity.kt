package nl.terwijn.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        this.init()
    }

    private fun init(){
        ivSupportImage.setImageResource(R.mipmap.ic_launcher)
        ivMainImage.setImageResource(R.mipmap.ic_launcher_round)
    }
}
