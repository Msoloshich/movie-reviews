package com.example.moviereviews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.moviereviews.databinding.PartDefaultLoadStateBinding

typealias TryAgainAction = () -> Unit

class DefaultLoadStateAdapter(private val tryAgainAction: TryAgainAction): LoadStateAdapter<Holder>() {

    override fun onBindViewHolder(holder: Holder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PartDefaultLoadStateBinding.inflate(inflater, parent, false)
        return Holder(binding, null, tryAgainAction)
    }
}