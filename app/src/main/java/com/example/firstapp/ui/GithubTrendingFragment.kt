package com.example.firstapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.adapter.RecyclerViewAdapter
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.viewModel.GithubTrendingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GithubTrendingFragment : Fragment(), PostClickHandler {

    private lateinit var recyclerAdapter: RecyclerViewAdapter

    private val viewModel: GithubTrendingViewModel by viewModels<GithubTrendingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerAdapter = RecyclerViewAdapter(this)
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        viewModel.recyclerListLiveData.observe(viewLifecycleOwner, Observer<GithubTrending> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.makeApiCall()
    }

    override fun clickedPostItem(item: Item) {

        Log.d("Fragment", "Clicked post item ${item.fullName}")
        val bundle = bundleOf("title" to item.fullName)
        findNavController().navigate(R.id.repoDetailFragment, bundle)
    }
}