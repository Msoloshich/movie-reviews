package com.example.moviereviews.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviereviews.R
import com.example.moviereviews.databinding.ActivityMovieReviewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieReviewsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieReviewsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ReviewsListFragment.newInstance())
            .addToBackStack(null)
            .commit()

        binding.bnvBottomBar.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.iReviews -> ReviewsListFragment.newInstance()
                R.id.iCritics -> CriticsListFragment.newInstance()
                else -> ReviewsListFragment.newInstance()
            }
            navigateToFragment(fragment)
            true
        }

    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit()
    }


}