package com.example.moviereviews.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.moviereviews.data.database.AppDatabase
import com.example.moviereviews.data.database.ReviewsDbModel
import com.example.moviereviews.data.mappers.ReviewsMapper
import com.example.moviereviews.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ReviewRemoteMediator(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService,
) : RemoteMediator<Int, ReviewsDbModel>() {
    private val reviewMapper = ReviewsMapper()

    private var pageIndex = 0


    override suspend fun initialize(): InitializeAction {
        var hasData = false
        appDatabase.withTransaction {
            pageIndex = appDatabase.reviewsDao().getCount() / 20
            hasData = pageIndex != 0
        }
        return if (!hasData) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }

    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReviewsDbModel>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    pageIndex = 1
                    0
                }

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> pageIndex
            }

            val reviews = apiService.getReviews(
                offset = loadKey * 20
            )
            Log.d("Test", "Load type $loadType")
            Log.d("Test", "Send request $pageIndex")

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.reviewsDao().clearAll()
                }
                val reviewsDtoToDao = reviews.results.map {
                    reviewMapper.mapReviewDtoToDbModel(it)
                }
                appDatabase.reviewsDao().insertReviewsList(reviewsDtoToDao)
            }

            pageIndex++
            MediatorResult.Success(
                endOfPaginationReached = !reviews.hasMore
            )
        } catch (e: IOException) {
            Log.d("Test Error", "Send error1 $e")
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            Log.d("Test Error", "Send error2 $e")
            MediatorResult.Error(e)
        }
    }
}