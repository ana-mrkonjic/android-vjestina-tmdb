package agency.five.tmdb.database

data class DetailsItem(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val score: Double,
    val genre: String,
    val releaseDate: String,
    val cast: List<CastMember>,
    val directors: List<CastMember>
    )
