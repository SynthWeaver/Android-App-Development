package nl.terwijn.placesofinterest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_place.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initViews()
    }

    private val places = arrayListOf<Place>()
    private val placeAdapter = PlaceAdapter(places)

    private fun initViews(){
        rvPlaces.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPlaces.adapter = this.placeAdapter

        for (i in Place.PLACE_NAMES.indices) {
            places.add(
                Place(Place.PLACE_NAMES[i],
                Place.PLACE_RES_DRAWABLE_IDS[i])
            )
        }

        placeAdapter.notifyDataSetChanged()
    }


    public class PlaceAdapter(private val places: List<Place>) :
        RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
        lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_place,
                    parent, false)
            )
        }

        override fun getItemCount(): Int {
            return places.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.bind(places[position])
        }

        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(place : Place) {
                itemView.ivPlace.setImageDrawable(context.getDrawable(place.imageResId))
                itemView.tvPlace.text = place.name
            }
        }
    }
}
