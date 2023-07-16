package com.example.moviereviews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviereviews.R
import com.example.moviereviews.databinding.CriticCardBinding
import com.example.moviereviews.domain.entity.Critic
import com.squareup.picasso.Picasso

class CriticsCardAdapter : ListAdapter<Critic, CriticCardViewHolder>(CriticCardDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriticCardViewHolder {
        val binding = CriticCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CriticCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriticCardViewHolder, position: Int) {
        val criticItem = getItem(position)
        with(holder.binding) {
            tvAuthorName.text = criticItem.name
            Picasso.get().load(criticItem.photoUrl).placeholder(R.drawable.baseline_person_24)
                .into(ivPhoto)
        }
    }

}