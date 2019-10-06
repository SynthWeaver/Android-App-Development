package nl.terwijn.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

const val ADD_STUDENT_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

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
    }

    private fun onAddClick() {
        val intent = Intent(this, StudentPortal::class.java)
        startActivityForResult(intent, ADD_STUDENT_PORTAL_REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_STUDENT_PORTAL_REQUEST_CODE -> {
                    val site = data!!.getParcelableExtra<Site>(EXTRA_SITE)
                    addSite(site)
                }
            }
        }
    }

    private fun addSite(site: Site?) {
        if (site != null) {
            println(site.name + site.url)
        }
    }
}
