package com.ghali.bored.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.ghali.bored.R
import com.ghali.bored.databinding.FragmentListBinding
import com.ghali.bored.db.Thing
import com.ghali.bored.model.MainViewModel
import java.lang.IndexOutOfBoundsException

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PLACE = "place"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    private var place: Int? = null
    lateinit var binding: FragmentListBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var list: List<Thing>
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            place = it.getInt(PLACE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = viewModel.getList(place!!)
        viewPager = binding.pager
        viewPager.adapter = PagerAdapter(this)
        binding.next.setOnClickListener { viewPager.currentItem++ }
    }

    private inner class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        // Adding 2 is a workaround to press the back button when it's the end of the list
        override fun getItemCount(): Int = list.size+2

        override fun createFragment(position: Int): Fragment = CardFragment.newInstance(displayNext(position))
    }

    // Forgives the first exception but not the second to press the back button
    var error = 0
    private fun displayNext(position: Int): String {
        var text = ""
        try {
            text = list[position].text
        } catch (e: IndexOutOfBoundsException) {
            if (error == 1) activity?.onBackPressed()
            else error++
        }
        return text
    }
}

