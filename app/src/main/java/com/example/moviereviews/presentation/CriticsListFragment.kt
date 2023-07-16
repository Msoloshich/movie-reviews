package com.example.moviereviews.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviereviews.databinding.FragmentCriticsListBinding
import com.example.moviereviews.presentation.adapter.CriticsCardAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CriticsListFragment: Fragment() {

    private var _binding: FragmentCriticsListBinding? = null
    private val viewModel by viewModels<MovieReviewsViewModel>()
    private val binding: FragmentCriticsListBinding
        get() = _binding ?: throw RuntimeException("Fragment FragmentCriticsListBinding not found")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriticsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCriticsListAdapter()
    }

    private fun setupCriticsListAdapter() {
        val adapter = CriticsCardAdapter()
        val rvCriticsCardList = binding.rvCriticsCardList
        rvCriticsCardList.adapter = adapter
        lifecycleScope.launch {
            viewModel.getCriticsList().collectLatest {
                adapter.submitList(it)
            }
        }
    }


    companion object {
        fun newInstance (): Fragment {
            return CriticsListFragment().apply {
                arguments = Bundle()
            }
        }
    }
}