package com.example.moviesuniverse.data.local

sealed class DaoResult<T> {
    class NotExist<T> : DaoResult<T>()
    data class Exist<T>(val item: T) : DaoResult<T>()
}