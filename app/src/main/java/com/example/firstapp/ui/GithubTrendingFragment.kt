package com.example.firstapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstapp.R
import com.example.firstapp.adapter.RecyclerViewAdapter
import com.example.firstapp.databinding.FragmentRecyclerListBinding
import com.example.firstapp.model.Item
import com.example.firstapp.util.Resource
import com.example.firstapp.viewModel.GithubTrendingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GithubTrendingFragment : Fragment(), PostClickHandler {

    private val recyclerAdapter: RecyclerViewAdapter by lazy { RecyclerViewAdapter(this) }


    private var _binding: FragmentRecyclerListBinding? = null
    private val binding: FragmentRecyclerListBinding
        get() = _binding!!

    private val viewModel: GithubTrendingViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        _binding = FragmentRecyclerListBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        _binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    private fun initViewModel() {
        viewModel.recyclerListLiveData.observe(viewLifecycleOwner, Observer { response ->
            binding.recyclerView.isVisible = response is Resource.Success
            if (response is Resource.Success) {
                binding.swipeToRefresh.isRefreshing = false
            }
            binding.noInternetConnection.isVisible = response is Resource.Error
            binding.progressBar.isVisible = response is Resource.Loading
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        recyclerAdapter.setUpdatedData(it.items)
                    }

                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        })
        viewModel.makeApiCall()
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.makeApiCall()
        }
    }

    override fun clickedPostItem(item: Item) {
        val bundle = bundleOf("title" to item.fullName)
        findNavController().navigate(R.id.repoDetailFragment, bundle)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}