package com.muramsyah.gitsid.todoapp.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.muramsyah.gitsid.todoapp.R
import com.muramsyah.gitsid.todoapp.adapter.HomeAdapter
import com.muramsyah.gitsid.todoapp.databinding.FragmentHomeBinding
import com.muramsyah.gitsid.todoapp.ui.add.AddUpdateFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val TAG = HomeFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel() {
        viewModel.allTodo.observe(viewLifecycleOwner, {

            val adapter = HomeAdapter(it)
            adapter.notifyDataSetChanged()

            adapter.onItemClicked = {
                val bundle = Bundle().apply {
                    putParcelable(AddUpdateFragment.EXTRA_DATA, it)
                }
                binding.root.findNavController().navigate(R.id.action_homeFragment_to_addUpdateFragment, bundle)
            }

            with(binding.rvCatatan) {
                this.adapter = adapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                setHasFixedSize(true)
            }

        })

        binding.fabAdd.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addUpdateFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        initViewModel()
    }

}