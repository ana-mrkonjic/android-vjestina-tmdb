package agency.five.tmdb.remote

import agency.five.tmdb.remote.HttpRoutes.BASE_IMAGE_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewMember(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val poster: String?,
    val image: String = BASE_IMAGE_URL+poster,
    @SerialName("job")
    val job: String
)
