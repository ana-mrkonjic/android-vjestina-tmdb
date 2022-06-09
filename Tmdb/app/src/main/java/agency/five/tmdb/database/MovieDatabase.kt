package agency.five.tmdb.database

import agency.five.tmdb.remote.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


class MovieDatabase {

    private var favoriteMovies = mutableListOf<MovieResponse>(
        MovieResponse(
            id = 1,
            title = "Title 1",
            overview = "Content 1",
            poster = "",
            imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
        ),
        MovieResponse(
            id = 2,
            title = "Title 2",
            overview = "Content 2",
            poster = "",
            imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
        ),
        MovieResponse(
            id = 3,
            title = "Title 3",
            overview = "Content 3",
            poster = "",
            imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
        )
    )

    private val favoriteMoviesFlow = MutableStateFlow(favoriteMovies)


    fun getFavoriteMovies(): Flow<MutableList<MovieResponse>> {
        return favoriteMoviesFlow
    }

    fun addFavoriteMovie(movie: MovieResponse) {
        val tempList = favoriteMoviesFlow.value.toMutableList()
        tempList.add(movie)
        favoriteMoviesFlow.value = tempList

    }

    fun removeFavoriteMovie(movie: MovieResponse) {
        val tempList = favoriteMoviesFlow.value.toMutableList()
        tempList.remove(movie)
        favoriteMoviesFlow.value = tempList
    }

}