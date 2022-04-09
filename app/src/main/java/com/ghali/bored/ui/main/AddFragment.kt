package com.ghali.bored.ui.main

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ghali.bored.R
import com.ghali.bored.databinding.FragmentAddBinding
import com.ghali.bored.model.MainViewModel

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.done.setOnClickListener {
            val text = binding.textInput.text.toString()
            val home = binding.homeCheckbox.isChecked
            val away = binding.awayCheckbox.isChecked
            if ((TextUtils.isEmpty(text)) or (!home and !away))
                Toast.makeText(context, "Please add text and select at least one place", Toast.LENGTH_SHORT).show()
            else {
                viewModel.addThing(
                    text,
                    home,
                    away
                )
                findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
            }
        }
    }
}