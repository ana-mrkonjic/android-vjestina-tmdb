package agency.five.tmdb.repository

import kotlinx.serialization.Serializable



data class MovieItem(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,

)