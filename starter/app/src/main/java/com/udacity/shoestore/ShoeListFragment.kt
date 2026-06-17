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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding

//class ShoeListFragment : Fragment() {
//
//    companion object {
//        fun newInstance() = ShoeListFragment()
//    }
//
//    private val viewModel: ShoeListViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.fragment_shoe_list, container, false)
//    }
//}

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels<ShoeListViewModel>()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // Observe the shoe list LiveData
        viewModel.shoeList.observe(viewLifecycleOwner) { shoes ->
            updateShoeList(shoes)
        }

        // Navigate to Shoe Detail screen
        binding.addShoeFab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }


        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    // 3. Handle the click action
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.loginFragment -> {
                // Navigate back to Login
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateShoeList(shoes: List<Shoe>) {
        val container = binding.shoeListContainer
        container.removeAllViews() // Clear old views to prevent duplicates

        for (shoe in shoes) {
            // Programmatically create a simple TextView or inflate a custom item layout
            val textView = TextView(context).apply {
                text = "${shoe.name}"
                textSize = 18f
                setPadding(0, 0, 0, 32)
            }
            container.addView(textView)
        }
    }
}