package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    // 1. Get a reference to the shared ViewModel (scoped to the Activity)
    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 2. Inflate the layout using Data Binding
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        // 3. Create a blank Shoe object to hold the data from the EditTexts
        // This matches the variable 'shoeViewModel' you defined in your XML
        val newShoe = Shoe("", "", "", "")
        binding.shoe = newShoe

        // 4. Set up the Save Button
        binding.saveButton.setOnClickListener {
            // Add the shoe that was updated via two-way data binding to our list
            viewModel.addShoe(newShoe)
            // Navigate back to the Shoe List
            findNavController().navigateUp()
        }

        // 5. Set up the Cancel Button
        binding.cancelButton.setOnClickListener {
            // Just go back without saving anything
            findNavController().navigateUp()
        }

        return binding.root
    }
}