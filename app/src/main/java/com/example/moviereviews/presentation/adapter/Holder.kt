package com.example.moviereviews.presentation.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviews.databinding.PartDefaultLoadStateBinding
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class Holder(
    private val binding: PartDefaultLoadStateBinding,
    private val swipeRefreshLayout: SwipeRefreshLayout?,
    private val tryAgainAction: TryAgainAction
): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.tryAgainButton.setOnClickListener { tryAgainAction() }
    }

    fun bind(loadState: LoadState) = with(binding) {
        messageTextView.isVisible = loadState is LoadState.Error
        tryAgainButton.isVisible = loadState is LoadState.Error
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.isRefreshing = loadState is LoadState.Loading
            progressBar.isVisible = false
        } else {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}