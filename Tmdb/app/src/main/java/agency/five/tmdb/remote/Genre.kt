package agency.five.tmdb.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Genre(
    @SerialName("id")
    val genreId: Int,
    @SerialName("name")
    val name: String
)
