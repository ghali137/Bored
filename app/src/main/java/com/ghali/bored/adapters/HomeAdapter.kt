package com.ghali.bored.adapters

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.ghali.bored.R
import com.ghali.bored.db.Thing
import com.ghali.bored.model.MainViewModel
import com.ghali.bored.ui.main.ListFragmentDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeAdapter(private val things: MutableList<Thing>, private val navController: NavController, private val viewModel: MainViewModel) : RecyclerView.Adapter<HomeAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val element: ConstraintLayout
        val cardView: CardView

        init {
            title = view.findViewById(R.id.goal_title)
            element = view.findViewById(R.id.element)
            cardView = view.findViewById(R.id.card_view)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.home_recyclerview, viewGroup, false)
        ViewCompat.setNestedScrollingEnabled(view, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
        holder.title.text = things[position].text
        holder.cardView.setOnClickListener { navController.navigate(ListFragmentDirections.actionListFragmentToSelf(things[position].id!!)) }
        holder.cardView.setOnLongClickListener {
            val dialog = DeleteDialogFragment{ delete(position) }
            dialog.show(manager, "tag")
            true
        }
    }
    fun delete(position: Int) {
        viewModel.deleteThing(things[position])
        things.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return things.size
    }
}

class DeleteDialogFragment(val delete: () -> Unit) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = MaterialAlertDialogBuilder(it, R.style.App_Material3_MaterialAlertDialog)
            builder.setTitle("Do you want to delete the Element?")
                .setPositiveButton("Delete",
                    DialogInterface.OnClickListener { dialog, id ->
                        delete()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}