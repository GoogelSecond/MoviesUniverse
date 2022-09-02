package com.example.moviesuniverse.data.remote.movies.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponse(
    @SerialName("completed")
    val completed: Boolean = false,
    @SerialName("countries")
    val countries: List<Country> = listOf(),
    @SerialName("coverUrl")
    val coverUrl: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("editorAnnotation")
    val editorAnnotation: String = "",
    @SerialName("endYear")
    val endYear: String = "",
    @SerialName("filmLength")
    val filmLength: Int = 0,
    @SerialName("genres")
    val genres: List<Genre> = listOf(),
    @SerialName("has3D")
    val has3D: Boolean = false,
    @SerialName("hasImax")
    val hasImax: Boolean = false,
    @SerialName("imdbId")
    val imdbId: String = "",
    @SerialName("isTicketsAvailable")
    val isTicketsAvailable: Boolean = false,
    @SerialName("kinopoiskId")
    val kinopoiskId: Int = 0,
    @SerialName("lastSync")
    val lastSync: String = "",
    @SerialName("logoUrl")
    val logoUrl: String = "",
    @SerialName("nameEn")
    val nameEn: String = "",
    @SerialName("nameOriginal")
    val nameOriginal: String = "",
    @SerialName("nameRu")
    val nameRu: String = "",
    @SerialName("posterUrl")
    val posterUrl: String = "",
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String = "",
    @SerialName("productionStatus")
    val productionStatus: String = "",
    @SerialName("ratingAgeLimits")
    val ratingAgeLimits: String = "",
    @SerialName("ratingAwait")
    val ratingAwait: String = "",
    @SerialName("ratingAwaitCount")
    val ratingAwaitCount: Int = 0,
    @SerialName("ratingFilmCritics")
    val ratingFilmCritics: Double = 0.0,
    @SerialName("ratingFilmCriticsVoteCount")
    val ratingFilmCriticsVoteCount: Int = 0,
    @SerialName("ratingGoodReview")
    val ratingGoodReview: String = "",
    @SerialName("ratingGoodReviewVoteCount")
    val ratingGoodReviewVoteCount: Int = 0,
    @SerialName("ratingImdb")
    val ratingImdb: Double = 0.0,
    @SerialName("ratingImdbVoteCount")
    val ratingImdbVoteCount: Int = 0,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: Double = 0.0,
    @SerialName("ratingKinopoiskVoteCount")
    val ratingKinopoiskVoteCount: Int = 0,
    @SerialName("ratingMpaa")
    val ratingMpaa: String = "",
    @SerialName("ratingRfCritics")
    val ratingRfCritics: Double = 0.0,
    @SerialName("ratingRfCriticsVoteCount")
    val ratingRfCriticsVoteCount: Int = 0,
    @SerialName("reviewsCount")
    val reviewsCount: Int = 0,
    @SerialName("serial")
    val serial: Boolean = false,
    @SerialName("shortDescription")
    val shortDescription: String = "",
    @SerialName("shortFilm")
    val shortFilm: Boolean = false,
    @SerialName("slogan")
    val slogan: String = "",
    @SerialName("startYear")
    val startYear: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("webUrl")
    val webUrl: String = "",
    @SerialName("year")
    val year: Int = 0
) {
    @Serializable
    data class Country(
        @SerialName("country")
        val country: String = ""
    )

    @Serializable
    data class Genre(
        @SerialName("genre")
        val genre: String = ""
    )
}