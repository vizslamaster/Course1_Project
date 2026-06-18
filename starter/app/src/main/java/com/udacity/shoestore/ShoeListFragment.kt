package com.udacity.shoestore


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import android.widget.LinearLayout


class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels<ShoeViewModel>()
    private lateinit var binding: FragmentShoeListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)


        viewModel.shoeList.observe(viewLifecycleOwner) { shoes ->
            updateShoeList(shoes)
        }

        binding.addShoeFab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    // 3. Handle the click action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.loginFragment) { // Match your menu ID
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateShoeList(shoes: List<Shoe>) {
        val container =
            binding.shoeListContainer
        container.removeAllViews()

        for (singleShoe in shoes) {
            val shoeItemView = TextView(context).apply {
                text =
                    "Name: ${singleShoe.name}\n" +
                            "Company: ${singleShoe.company}\n" +
                            "Size: ${singleShoe.size}\n---"
                textSize = 18f
                setPadding(20, 20, 20, 20)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            container.addView(shoeItemView)
        }
    }
}