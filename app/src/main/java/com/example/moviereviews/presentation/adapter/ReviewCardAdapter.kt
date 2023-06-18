package com.example.moviereviews.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.moviereviews.databinding.ReviewCardBinding
import com.example.moviereviews.domain.entity.Review
import com.squareup.picasso.Picasso

class ReviewCardAdapter: PagingDataAdapter<Review, ReviewCardViewHolder>(ReviewCardDiffCallback) {

    var onOpenReviewClickListener:OnOpenReviewClickListener? = null
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
                tvTitle.text = reviewItem?.title
                tvAuthor.text = "Author: " + reviewItem?.author
                tvShortDesc.text = reviewItem?.shortDescription
                tvDate.text= "Date: ${reviewItem?.publicationDate}"
                Picasso.get().load(reviewItem?.imageUrl).into(ivReview)
                btLinkButton.setOnClickListener {
                    onOpenReviewClickListener?.onReadReviewClick(reviewItem?.reviewUrl ?: "")

            }
        }
    }


    interface OnOpenReviewClickListener {
        fun onReadReviewClick(reviewUrl: String)
    }
}