package com.example.moviereviews.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviereviews.domain.entity.Critic

object CriticCardDiffCallback: DiffUtil.ItemCallback<Critic>() {
    override fun areItemsTheSame(oldItem: Critic, newItem: Critic): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Critic, newItem: Critic): Boolean {
        return oldItem == newItem
    }
}