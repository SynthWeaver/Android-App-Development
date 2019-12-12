package nl.terwijn.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.sites.view.*

const val ADD_STUDENT_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val sites = arrayListOf<Site>()
    private val siteAdapter = SiteAdapter(sites)

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

        rvSites.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvSites.adapter = this.siteAdapter
    }

    private fun onAddClick() {
        val intent = Intent(this, StudentPortal::class.java)
        startActivityForResult(intent, ADD_STUDENT_PORTAL_REQUEST_CODE)
        startActivity()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_STUDENT_PORTAL_REQUEST_CODE -> {
                    val site = data!!.getParcelableExtra<Site>(EXTRA_SITE)
                    sites.add(site)
                    siteAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    public class SiteAdapter(private val sites: List<Site>) :
        RecyclerView.Adapter<SiteAdapter.ViewHolder>() {
        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.sites, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return sites.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.bind(sites[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(site : Site) {
                itemView.btnName.text = String.format("%s\n%s", site.name, site.url)
                itemView.btnName.setOnClickListener {
                    openUrl(site.url)
                }
            }
        }

        private fun openUrl(url: String) {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(url))
        }
    }
}
