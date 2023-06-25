package com.example.moviereviews.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviereviews.databinding.FragmentCriticsListBinding


class CriticsListFragment: Fragment() {

    private var _binding: FragmentCriticsListBinding? = null
    private val binding: FragmentCriticsListBinding
        get() = _binding ?: throw RuntimeException("Fragment FragmentCriticsListBinding not found")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriticsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance (): Fragment {
            return CriticsListFragment().apply {
                arguments = Bundle()
            }
        }
    }
}