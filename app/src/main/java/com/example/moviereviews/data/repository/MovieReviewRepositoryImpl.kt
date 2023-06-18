package com.example.moviereviews.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviereviews.data.database.AppDatabase
import com.example.moviereviews.data.database.ReviewsDbModel
import com.example.moviereviews.data.mappers.CriticsMapper
import com.example.moviereviews.data.mappers.ReviewsMapper
import com.example.moviereviews.data.network.ApiFactory
import com.example.moviereviews.domain.entity.Critic
import com.example.moviereviews.domain.entity.Review
import com.example.moviereviews.domain.repository.MovieReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class MovieReviewRepositoryImpl(application: Application) : MovieReviewRepository {

    private val database = AppDatabase.getInstance(application)
    private val apiService = ApiFactory.apiService
    private val reviewsDao = database.reviewsDao()
    private val criticsDao = database.criticsDao()

    private val reviewMapper = ReviewsMapper()
    private val criticsMapper = CriticsMapper()

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

    override suspend fun loadData() {
        val reviewsRaw = apiService.getReviews()
        val reviewsDtoToDao = reviewsRaw.results.map {
            reviewMapper.mapReviewDtoToDbModel(it)
        }
        reviewsDao.insertReviewsList(reviewsDtoToDao)
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getPagedReviewsDbModel():  Flow<PagingData<ReviewsDbModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20, enablePlaceholders = true),
            remoteMediator = ReviewRemoteMediator(appDatabase=database, apiService=apiService),
        ){
            database.reviewsDao().pagingSource()
        }.flow
    }

    override fun getPagedReviews(): Flow<PagingData<Review>> {
        return getPagedReviewsDbModel().map { page -> page.map { reviewMapper.mapReviewModelToEntity(it) }  }
    }
}