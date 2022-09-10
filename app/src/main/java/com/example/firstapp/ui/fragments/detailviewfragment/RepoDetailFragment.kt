package com.example.firstapp.ui.fragments.detailviewfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.firstapp.R
import com.example.firstapp.databinding.FragmentRepoDetailBinding
import com.example.firstapp.ui.extensions.firstLetterUppercase
import com.example.firstapp.ui.extensions.setTextOrHide
import com.example.firstapp.ui.views.RepoDetailViewState
import com.example.firstapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_FULL_NAME = "full_name"
private const val ARG_IMAGE_URL = "avatar_url"
private const val ARG_DESCRIPTION = "description"
private const val ARG_DEFAULT_BRANCH = "default_branch"
private const val ARG_LOGIN = "login"


/**
 * A simple [Fragment] subclass.
 * Use the [RepoDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RepoDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var full_name: String? = null
    private var avatar_url: String? = null
    private var description: String? = null
    private val viewModel: RepoDetailViewModel by viewModels()
    private var readmeContent: String? = null
    private var default_branch: String? = null
    private var loginName: String? = null
    private var _binding: FragmentRepoDetailBinding? = null
    private val binding: FragmentRepoDetailBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            full_name = it.getString(ARG_FULL_NAME)
            avatar_url = it.getString(ARG_IMAGE_URL)
            description = it.getString(ARG_DESCRIPTION)
            default_branch = it.getString(ARG_DEFAULT_BRANCH)
            loginName = it.getString(ARG_LOGIN)
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
        viewModel.makeApiCallForReadme(full_name, default_branch)
        observeReadmeLiveData()
    }

    private fun observeReadmeLiveData() {
        viewModel.readmeData.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Success -> {
                    readmeContent = response.data
                    binding.firstCard.render(
                        RepoDetailViewState(
                            "$avatar_url",
                            " ${full_name?.firstLetterUppercase()}",
                            "$description",
                        )
                    )
                    binding.contentReadme.setTextOrHide("$readmeContent")
                    binding.collapsingToolbar.title = loginName?.firstLetterUppercase()
                }
                is Resource.Error -> {
                    binding.firstCard.render(
                        RepoDetailViewState(
                            "$avatar_url",
                            " ${full_name?.firstLetterUppercase()}",
                            "$description",
                        )
                    )
                    binding.collapsingToolbar.title = loginName?.firstLetterUppercase()
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RepoDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RepoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_FULL_NAME, param1)
                }
            }
    }
}



