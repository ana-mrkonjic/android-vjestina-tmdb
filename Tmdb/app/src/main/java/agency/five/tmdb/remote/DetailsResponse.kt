package agency.five.tmdb.remote


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable;

@Serializable
data class DetailsResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("backdrop_path")
    val poster: String,
    val imageUrl: String = HttpRoutes.BASE_IMAGE_URL + poster,
    @SerialName("vote_average")
    val score: Double,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("release_date")
    val releaseDate: String,
)

