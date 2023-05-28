package com.example.moviereviews.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.reviewList.observe(this) {
            adapter.submitList(it)
        }

    }
}