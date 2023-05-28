package com.example.moviereviews.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviereviews.domain.entity.Review

object ReviewCardDiffCallback: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.publicationDate == newItem.publicationDate
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}