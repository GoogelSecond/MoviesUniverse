package com.example.moviesuniverse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesuniverse.data.local.movies.MoviesDao
import com.example.moviesuniverse.data.local.movies.MoviesDetailDao
import com.example.moviesuniverse.data.local.movies.RemoteKeysDao
import com.example.moviesuniverse.data.local.movies.models.MovieDetailEntity
import com.example.moviesuniverse.data.local.movies.models.MovieEntity
import com.example.moviesuniverse.data.local.movies.models.RemoteKeyEntity
import com.example.moviesuniverse.data.local.staff.MoviesStaffDao
import com.example.moviesuniverse.data.local.staff.StaffDetailDao
import com.example.moviesuniverse.data.local.staff.model.MovieStaffEntity
import com.example.moviesuniverse.data.local.staff.model.StaffDetailEntity

@Database(
    entities = [
        MovieEntity::class,
        RemoteKeyEntity::class,
        MovieDetailEntity::class,
        MovieStaffEntity::class,
        StaffDetailEntity::class
    ],
    version = DB_VERSION,
    exportSchema = false
)

abstract class DataBase : RoomDatabase() {
    abstract fun getMovieDao(): MoviesDao
    abstract fun getMovieDetailDao(): MoviesDetailDao
    abstract fun getRemoteKeyDao(): RemoteKeysDao
    abstract fun getMovieStaffDao(): MoviesStaffDao
    abstract fun getStaffDetailDao(): StaffDetailDao
}

private const val DB_VERSION = 1