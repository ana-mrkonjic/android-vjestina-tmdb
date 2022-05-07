package agency.five.tmdb.database

import agency.five.tmdb.repository.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow


class MovieDatabase {

    private var favoriteMovies = mutableListOf<MovieItem>(
        MovieItem(
            id = 1,
            title = "Title 1",
            overview = "Content 1",
            imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
        ),
        MovieItem(
            id = 2,
            title = "Title 2",
            overview = "Content 2",
            imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
        ),
        MovieItem(
            id = 3,
            title = "Title 3",
            overview = "Content 3",
            imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
        )
    )

    private val favoriteMoviesFlow = MutableStateFlow(favoriteMovies)


    fun getFavoriteMovies(): Flow<MutableList<MovieItem>> {
        return favoriteMoviesFlow
    }

    fun addFavoriteMovie(movie: MovieItem) {
        val tempList = favoriteMoviesFlow.value.toMutableList()
        tempList.add(movie)
        favoriteMoviesFlow.value = tempList

    }

    fun removeFavoriteMovie(movie: MovieItem) {
        val tempList = favoriteMoviesFlow.value.toMutableList()
        tempList.remove(movie)
        favoriteMoviesFlow.value = tempList
    }

}