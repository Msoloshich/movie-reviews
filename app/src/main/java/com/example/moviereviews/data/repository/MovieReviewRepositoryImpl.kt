package com.example.moviereviews.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.moviereviews.data.database.AppDatabase
import com.example.moviereviews.data.mappers.CriticsMapper
import com.example.moviereviews.data.mappers.ReviewsMapper
import com.example.moviereviews.domain.entity.Critic
import com.example.moviereviews.domain.entity.Review
import com.example.moviereviews.domain.repository.MovieReviewRepository

class MovieReviewRepositoryImpl(application: Application) : MovieReviewRepository {

    private val database = AppDatabase.getInstance(application)
    private val reviewsDao = database.reviewsDao()
    private val criticsDao = database.criticsDao()

    private val reviewMapper = ReviewsMapper()
    private val criticsMapper = CriticsMapper()

    override fun getReviewList(): LiveData<List<Review>> =
        reviewsDao.getReviewsList().map { reviewsData ->
            reviewsData.map {
                reviewMapper.mapReviewModelToEntity(it)
            }
        }

    override fun getCriticList(): LiveData<List<Critic>> =
        criticsDao.getCriticsList().map { criticsData ->
            criticsData.map {
                criticsMapper.mapCriticsDbModelToEntity(it)
            }
        }


    override fun getCritic(id: Int): LiveData<Critic> =
        criticsDao.getCritic(id).map {
            criticsMapper.mapCriticsDbModelToEntity(it)
        }

}