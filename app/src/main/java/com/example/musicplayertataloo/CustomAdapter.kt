package com.example.musicplayertataloo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext


class CustomAdapter(var context: Context,private val dataSet: ArrayList<Music>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var tv: TextView
        lateinit var line: LinearLayout
        lateinit var image: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tv = view.findViewById(R.id.tv_item)
            image = view.findViewById(R.id.image_item)
            line = view.findViewById(R.id.layout_item)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.custom_item_music, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tv.text = dataSet[position].name
        Picasso.get().load(dataSet[position].image).into(viewHolder.image)
        viewHolder.line.setOnClickListener {
            context.startActivity(Intent(context,PlayerActivity::class.java).putExtra("position",position))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
