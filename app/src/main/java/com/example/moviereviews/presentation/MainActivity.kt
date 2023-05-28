package com.example.moviereviews.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviereviews.databinding.ActivityMainBinding
import com.example.moviereviews.presentation.adapter.ReviewCardAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val binding by lazy {
            ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = ReviewCardAdapter()
        val rvReviewCardList = binding.rvReviewCardList
        rvReviewCardList.adapter = adapter
        adapter.onOpenReviewClickListener = object : ReviewCardAdapter.OnOpenReviewClickListener {
            override fun onReadReviewClick(reviewUrl: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(reviewUrl))
                startActivity(browserIntent)
            }
        }
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.reviewList.observe(this) {
            adapter.submitList(it)
        }

    }
}