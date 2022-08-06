 package com.example.firstapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.adapter.RecyclerViewAdapter
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.viewModel.PostViewModel

class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_recycler_list, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        

        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
    }

     private fun initViewModel(){
            val viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
            viewModel.getRecyclerListObserver().observe(viewLifecycleOwner,Observer<GithubTrending>{
                if(it != null){
                    recyclerAdapter.setUpdatedData(it.items)
                }
                else{
                    Toast.makeText(activity,"Error in getting data", Toast.LENGTH_SHORT).show()
                }
            })

         viewModel.makeApiCall()
     }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()

    }
}