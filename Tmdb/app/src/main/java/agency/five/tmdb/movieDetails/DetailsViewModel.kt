package agency.five.tmdb.movieDetails

import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.*

class DetailsViewModel : ViewModel(){

    val actors = listOf(
        CastMember("Leonardo DiCaprio", "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg", "Jack Dawson"),
        CastMember("Kate Winslet", "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg", "Rose De."))

    val directors = listOf(
        CastMember("Don Heck", "", "Director"),
        CastMember("Jack Kirby", "", "Director")
        )

    val movie = DetailsItem(3, "Titanic", "Movie about..", "", 76.0, "drama", "1997-12-12", listOf())
}