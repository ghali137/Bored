package com.ghali.bored.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ghali.bored.R
import com.ghali.bored.db.Thing

/*class MainAdapter(private val dataSet: MutableList<Thing>, private val startDragListener: StartDragListener, private val removeClickListener: (thing: Thing) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listText: TextView
        val remove: ImageButton
        val handle: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            listText = view.findViewById(R.id.list_text)
            remove = view.findViewById(R.id.remove)
            handle = view.findViewById(R.id.handle)
        }
    }
    private lateinit var recyclerView: RecyclerView

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_recyclerview, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onAttachedToRecyclerView(mRecyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(mRecyclerView)
        recyclerView = mRecyclerView
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.listText.text = dataSet[position].text
        viewHolder.remove.setOnClickListener {
            removeClickListener(dataSet[position])
            dataSet.removeAt(position)
            recyclerView.adapter?.notifyItemRemoved(position)
        }
        viewHolder.handle.setOnTouchListener(OnTouchListener { _, event ->
            if (event.action ==
                MotionEvent.ACTION_DOWN
            ) {
                startDragListener.requestDrag(viewHolder)
            }
            false
        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size


}*/
