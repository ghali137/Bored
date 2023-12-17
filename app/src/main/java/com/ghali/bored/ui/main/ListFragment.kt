package com.ghali.bored.ui.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ghali.bored.R
import com.ghali.bored.adapters.HomeAdapter
import com.ghali.bored.databinding.FragmentListBinding
import com.ghali.bored.db.Thing
import com.ghali.bored.model.MainViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val PARENT = "Parent"


class ListFragment : Fragment() {

    val args: ListFragmentArgs by navArgs()

    private lateinit var viewPager: ViewPager2

    private var parent: Long? = null
    lateinit var binding: FragmentListBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var list: List<Thing>
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = try {
            args.parent
        } catch (e: java.lang.Exception) {
            0
        }
        Log.d("parent", parent.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.setDecorFitsSystemWindows(false);
        }
        binding = FragmentListBinding.inflate(inflater)
        val argParent = parent ?: 0
        binding.list.adapter = HomeAdapter(viewModel.getChildrenOf(argParent).toMutableList()   , findNavController(), viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.pager
        // viewPager.adapter = PagerAdapter(this)
        binding.add.setOnClickListener { findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment2(parent!!)) }
    }

    /*private inner class PagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = list.size

        override fun createFragment(position: Int): Fragment = CardFragment.newInstance(list[position].text)
    }
*/
}

