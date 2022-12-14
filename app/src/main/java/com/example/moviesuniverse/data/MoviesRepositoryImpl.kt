package com.example.moviesuniverse.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviesuniverse.data.local.DaoResult
import com.example.moviesuniverse.data.local.MoviesLocalDataSource
import com.example.moviesuniverse.data.local.movies.models.MovieEntity
import com.example.moviesuniverse.data.paging.MoviesSearchRemoteMediator
import com.example.moviesuniverse.data.paging.MoviesTop250RemoteMediator
import com.example.moviesuniverse.data.remote.ApiResult
import com.example.moviesuniverse.data.remote.MoviesRemoteDataSource
import com.example.moviesuniverse.domain.MoviesRepository
import com.example.moviesuniverse.domain.models.MovieDetail
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class MoviesRepositoryImpl(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getTop250Movies(): Flow<PagingData<MovieEntity>> {
        val moviesTop250RemoteMediator: MoviesTop250RemoteMediator
                by inject(MoviesTop250RemoteMediator::class.java) { parametersOf(TYPE_TOP_250) }

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { localDataSource.getMovieEntityPagingSource(TYPE_TOP_250) },
            remoteMediator = moviesTop250RemoteMediator
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun searchMovies(query: String): Flow<PagingData<MovieEntity>> {
        val moviesSearchRemoteMediator: MoviesSearchRemoteMediator
                by inject(MoviesSearchRemoteMediator::class.java) { parametersOf(query) }

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { localDataSource.getMovieEntityPagingSource(query) },
            remoteMediator = moviesSearchRemoteMediator
        ).flow
    }

    @OptIn(FlowPreview::class)
    override suspend fun getDetailMovie(id: String): Flow<ApiResult<MovieDetail>> {
        return localDataSource.getMovieDetailById(id).flatMapConcat { daoResult ->
            if (daoResult is DaoResult.Exist) {
                flow { emit(ApiResult.Success(daoResult.data)) }
            } else {
                getMovieDetailApiFlow(id)
            }
        }
    }

    @OptIn(FlowPreview::class)
    private suspend fun getMovieDetailApiFlow(id: String): Flow<ApiResult<MovieDetail>> {
        return remoteDataSource.getMovieDetail(id).flatMapConcat { response ->
            when (response) {
                is ApiResult.Error -> flow { emit(ApiResult.Error(response.error)) }
                is ApiResult.Success -> {
                    localDataSource.insertMovieDetail(response.data)

                    flow {
                        localDataSource.getMovieDetailById(id).map { daoResult ->
                            if (daoResult is DaoResult.Exist) {
                                ApiResult.Success(daoResult.data)
                            } else {
                                emit(ApiResult.Error(RuntimeException(EXCEPTION_MESSAGE)))
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val PAGE_SIZE = 20

        private const val EXCEPTION_MESSAGE = "Movie not exist in base after insert"

        private const val TYPE_TOP_250 = "TOP_250_BEST_FILMS"
    }
}