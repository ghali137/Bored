package com.ghali.bored.ui.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.ghali.bored.databinding.FragmentAddBinding
import com.ghali.bored.db.Thing
import com.ghali.bored.model.MainViewModel

class AddFragment : Fragment() {

    val args: AddFragmentArgs by navArgs()

    lateinit var binding: FragmentAddBinding
    private var parent: Long? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parent = args.parent
        Log.d("parent", parent.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val window = activity?.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.setDecorFitsSystemWindows(true);
        }
        binding = FragmentAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.save.setOnClickListener {
            val thing = Thing(text = binding.title.text.toString(), childOf = parent)
            viewModel.insert(thing)
            findNavController().popBackStack()
        }
    }


}