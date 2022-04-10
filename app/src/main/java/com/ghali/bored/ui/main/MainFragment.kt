package com.ghali.bored.ui.main

import MainAdapter
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ghali.bored.constants.AWAY
import com.ghali.bored.constants.HOME
import com.ghali.bored.databinding.FragmentMainBinding
import com.ghali.bored.db.Thing
import com.ghali.bored.model.MainViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
interface StartDragListener {
    fun requestDrag(viewHolder: RecyclerView.ViewHolder?)
}
class MainFragment : Fragment(), StartDragListener {

    private val viewModel: MainViewModel by viewModels()

    lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerView: RecyclerView
    val anim_duration: Long = 200
    lateinit var list: MutableList<Thing>
    var current_place: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        recyclerView = binding.editList
        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.add.setOnClickListener { findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment()) }
        binding.constraintLayout.setOnClickListener { hideList() }
        binding.atHome.setOnClickListener { goToList(HOME) }
        binding.atHome.setOnLongClickListener { showList(HOME) }
        binding.away.setOnClickListener { goToList(AWAY) }
        binding.away.setOnLongClickListener { showList(AWAY) }
    }

    private fun showList(place: Int): Boolean {
        current_place = place
        list = viewModel.getList(place).toMutableList()
        if (list.isEmpty()) {
            toast()
            return true
        }
        recyclerView.adapter = MainAdapter(list, this) { thing -> viewModel.deleteThing(thing) }
        binding.CardView.apply {
            if (visibility != View.VISIBLE) {
                alpha = 0f
                visibility = View.VISIBLE
                animate()
                    .alpha(1f)
                    .setDuration(anim_duration)
                    .setListener(null)
            }
        }
        return true
    }

    private fun goToList(place: Int) {
        if (viewModel.getList(place).isEmpty())
            toast()
        else findNavController().navigate(MainFragmentDirections.actionMainFragmentToListFragment(place))
    }

    private fun hideList() {
        binding.CardView.apply {
            animate()
                .alpha(0f)
                .setDuration(anim_duration)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.INVISIBLE
                    }
                })
        }
    }
    private val simpleCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                Collections.swap(list, fromPosition, toPosition)
                viewModel.swap(list[fromPosition].id!!, list[toPosition].id!!, current_place!!)
                recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
                return false
            }

            override fun isLongPressDragEnabled() = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        }

    private fun toast() {
        Toast.makeText(
            context,
            "Add a thing!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let { itemTouchHelper.startDrag(it) }
    }
}