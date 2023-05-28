package com.example.moviereviews.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviereviews.domain.entity.Review
import androidx.recyclerview.widget.ListAdapter
import com.example.moviereviews.databinding.ReviewCardBinding
import com.squareup.picasso.Picasso

class ReviewCardAdapter(): ListAdapter<Review, ReviewCardViewHolder>(ReviewCardDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewCardViewHolder {
        val binding = ReviewCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ReviewCardViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ReviewCardViewHolder, position: Int) {
        val reviewItem= getItem(position)
        with(holder.binding) {
            with(reviewItem) {
                tvTitle.text = title
                tvAuthor.text = "Author: $author"
                tvShortDesc.text = shortDescription
                tvDate.text= "Date: $publicationDate"
                Picasso.get().load(imageUrl).into(ivReview)
            }
        }
    }


}