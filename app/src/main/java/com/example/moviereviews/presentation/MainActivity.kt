package com.example.moviereviews.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviereviews.databinding.ActivityMainBinding
import com.example.moviereviews.presentation.adapter.DefaultLoadStateAdapter
import com.example.moviereviews.presentation.adapter.Holder
import com.example.moviereviews.presentation.adapter.ReviewCardAdapter
import com.example.moviereviews.presentation.adapter.TryAgainAction
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var mainLoadStateHolder: Holder

    private lateinit var viewModel: MainActivityViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
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

}