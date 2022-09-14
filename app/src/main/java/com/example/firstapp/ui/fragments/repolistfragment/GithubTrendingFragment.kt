package com.example.firstapp.ui.fragments.repolistfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.ui.PostClickHandler
import com.example.firstapp.ui.extensions.ImageTitleDescButtonListener
import com.example.firstapp.ui.views.ImageTitleDescriptionButtonViewState
import com.example.firstapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GithubTrendingFragment : Fragment(), PostClickHandler {

    private val recyclerAdapter: RecyclerViewAdapter by lazy { RecyclerViewAdapter(this) }
    private var _binding: FragmentRecyclerListBinding? = null
    private val binding: FragmentRecyclerListBinding
        get() = _binding!!
    private var flag = false
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
        binding.noInternetConnection.listener = object : ImageTitleDescButtonListener {
            override fun onButtonClick() {
                viewModel.makeApiCallForRepos()
            }
        }
    }

    private fun initViewModel() {
        if(viewModel.recyclerListLiveData.value?.data?.items?.isNotEmpty() == true){
            viewModel.fetchFromDatabase()
            observeRecyclerLiveData()
            flag = true
        } else {
            viewModel.makeApiCallForRepos()
            observeRecyclerLiveData()
        }
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.makeApiCallForRepos()
            observeRecyclerLiveData()
        }
    }

    private fun observeRecyclerLiveData() {
        viewModel.recyclerListLiveData.observe(viewLifecycleOwner, Observer { response ->
            binding.recyclerView.isVisible = response is Resource.Success
            if (response is Resource.Success) {
                binding.swipeToRefresh.isRefreshing = false
            }
            binding.noInternetConnection.isVisible = response is Resource.Error
            binding.progressBar.isVisible = response is Resource.Loading
            when (response) {
                is Resource.Success -> {
                    if(!flag) {
                        response.data?.let {
                            recyclerAdapter.setUpdatedData(it.items)
                        }
                    }
                }
                is Resource.Error -> {
                    with(binding) {
                        noInternetConnection.render(
                            ImageTitleDescriptionButtonViewState(
                                R.drawable.nointernetconnection,
                                getString(R.string.whoops),
                                getString(R.string.no_internet_connection_description),
                                getString(R.string.pull_down_to_refresh_btn)
                            )
                        )
                    }
                }
                is Resource.Loading -> {

                }
            }
        })
    }

    override fun clickedPostItem(item: Item) {
        val bundle = bundleOf(
            "item" to item
        )

        findNavController().navigate(R.id.repoDetailFragment, bundle)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}