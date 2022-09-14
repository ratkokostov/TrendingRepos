package com.example.firstapp.ui.fragments.detailviewfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentRepoDetailBinding
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.ui.extensions.firstLetterUppercase
import com.example.firstapp.ui.extensions.setTextOrHide
import com.example.firstapp.ui.views.RepoDetailViewState
import com.example.firstapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ITEM = "item"

/**
 * A simple [Fragment] subclass.
 * Use the [RepoDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RepoDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var item: Item? = null
    private var readmeContent: String? = null
    private val viewModel: RepoDetailViewModel by viewModels()
    private var _binding: FragmentRepoDetailBinding? = null
    private val binding: FragmentRepoDetailBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable(ARG_ITEM) as Item?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_detail, container, false)
        _binding = FragmentRepoDetailBinding.bind(view)
        return view
    }

    private fun initViewModel() {
        viewModel.makeApiCallForReadme(item?.full_name, item?.default_branch, item?.id)
        observeReadmeLiveData()
    }

    private fun observeReadmeLiveData() {
        viewModel.readmeData.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Success -> {
                    readmeContent = response.data
                    binding.firstCard.render(
                        RepoDetailViewState(
                            "${item?.owner?.avatarUrl}",
                            " ${item?.full_name?.firstLetterUppercase()}",
                            "$item?.description",
                        )
                    )
                    binding.contentReadme.setTextOrHide("$readmeContent")
                    binding.collapsingToolbar.title = item?.owner?.login?.firstLetterUppercase()

                }
                is Resource.Error -> {

                    binding.firstCard.render(
                        RepoDetailViewState(
                            "${item?.owner?.avatarUrl}",
                            " ${item?.full_name?.firstLetterUppercase()}",
                            "$item?.description",
                        )
                    )
                    binding.collapsingToolbar.title = item?.owner?.login?.firstLetterUppercase()
                    binding.cardView.isVisible = false
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

}



