package com.example.moviesuniverse.data.remote

import com.example.moviesuniverse.data.local.staff.model.MovieStaffEntity
import kotlinx.coroutines.flow.Flow

interface MovieStaffRemoteDataSource {

    suspend fun getMovieStaff(movieId: String): Flow<ApiResult<List<MovieStaffEntity>>>
}