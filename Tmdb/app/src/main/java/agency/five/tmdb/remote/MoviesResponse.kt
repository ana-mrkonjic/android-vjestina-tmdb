package agency.five.tmdb.remote

import io.ktor.http.ContentType.Application.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.*
import kotlinx.serialization.json.Json


@Serializable
data class MoviesResponse(

    @SerialName("results")
    val movies: List<MovieResponse>
)


