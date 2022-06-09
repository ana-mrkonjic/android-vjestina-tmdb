package agency.five.tmdb.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MembersResponse(
    @SerialName("cast")
    val cast: List<CastMember>,
    @SerialName("crew")
    val crew: List<CrewMember>
)
