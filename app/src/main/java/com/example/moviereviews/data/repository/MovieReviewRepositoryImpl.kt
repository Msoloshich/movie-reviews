package com.example.moviereviews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.moviereviews.data.database.AppDatabase
import com.example.moviereviews.data.database.ReviewsDbModel
import com.example.moviereviews.data.mappers.CriticsMapper
import com.example.moviereviews.data.mappers.ReviewsMapper
import com.example.moviereviews.data.network.ApiService
import com.example.moviereviews.domain.entity.Critic
import com.example.moviereviews.domain.entity.Review
import com.example.moviereviews.domain.repository.MovieReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class MovieReviewRepositoryImpl(private val apiService: ApiService, private val database: AppDatabase) : MovieReviewRepository {

    private val reviewsDao = database.reviewsDao()
    private val criticsDao = database.criticsDao()

    private val reviewMapper = ReviewsMapper()
    private val criticsMapper = CriticsMapper()

    override fun getCriticList(): Flow<List<Critic>> =
        criticsDao.getCriticsList().map { criticsData ->
            criticsData.map {
                criticsMapper.mapCriticsDbModelToEntity(it)
            }
        }


    override fun getCritic(name: String): Flow<Critic> =
        criticsDao.getCritic(name).map {
            criticsMapper.mapCriticsDbModelToEntity(it)
        }

    override suspend fun loadData() {
        val criticsRaw = apiService.getCritics()
        val criticsDtoToDao = criticsRaw.results.map {
            criticsMapper.mapCriticsDtoToDbModel(it)
        }
        criticsDao.insertCriticsList(criticsDtoToDao)
    }

    @OptIn(ExperimentalPagingApi::class)
    private fun getPagedReviewsDbModel():  Flow<PagingData<ReviewsDbModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true,  initialLoadSize = 10),
            remoteMediator = ReviewRemoteMediator(appDatabase=database, apiService=apiService),
        ){
            reviewsDao.pagingSource()
        }.flow
    }

    override fun getPagedReviews(): Flow<PagingData<Review>> {
        return getPagedReviewsDbModel().map { page -> page.map { reviewMapper.mapReviewModelToEntity(it) }  }
    }
}