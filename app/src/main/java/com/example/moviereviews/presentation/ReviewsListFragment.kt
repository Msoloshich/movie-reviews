package com.example.moviereviews.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviereviews.databinding.FragmentReviewsListBinding
import com.example.moviereviews.presentation.adapter.DefaultLoadStateAdapter
import com.example.moviereviews.presentation.adapter.Holder
import com.example.moviereviews.presentation.adapter.ReviewCardAdapter
import com.example.moviereviews.presentation.adapter.TryAgainAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewsListFragment: Fragment() {
    private lateinit var mainLoadStateHolder: Holder

    private val viewModel by viewModels<MovieReviewsViewModel>()

    private var _binding: FragmentReviewsListBinding? = null
    private val binding: FragmentReviewsListBinding
        get() = _binding ?: throw RuntimeException("Fragment ReviewsListBinding not found")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupReviewsList()
    }


    private fun setupReviewsList() {
        val adapter = ReviewCardAdapter()
        adapter.onOpenReviewClickListener = object : ReviewCardAdapter.OnOpenReviewClickListener {
            override fun onReadReviewClick(reviewUrl: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(reviewUrl))
                startActivity(browserIntent)
            }
        }

        val tryAgainAction: TryAgainAction = { adapter.retry() }
        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)
        val rvReviewCardList = binding.rvReviewCardList
        rvReviewCardList.adapter = adapterWithLoadState
        mainLoadStateHolder = Holder(
            binding.loadStateView,
            binding.swipeRefreshLayout,
            tryAgainAction
        )

        observeReviews(adapter)
        observeLoadState(adapter)
        setupSwipeToRefresh(adapter)

    }

    @OptIn(FlowPreview::class)
    private fun observeLoadState(adapter: ReviewCardAdapter) {
        lifecycleScope.launch {
            adapter.loadStateFlow.debounce(200).collectLatest { state ->
                mainLoadStateHolder.bind(state.append)
            }
        }
    }

    private fun setupSwipeToRefresh(adapter: ReviewCardAdapter) {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }


    private fun observeReviews(adapter: ReviewCardAdapter) {
        lifecycleScope.launch {
            viewModel.reviewsLiveData.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    companion object {
        fun newInstance (): Fragment {
            return ReviewsListFragment().apply {
                arguments = Bundle()
            }
        }
    }
}