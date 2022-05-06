package agency.five.tmdb.database

import agency.five.tmdb.repository.MovieItem
import kotlinx.coroutines.flow.Flow
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


    fun getFavoriteMovies(): Flow<MutableList<MovieItem>> = flow {
        favoriteMovies
        emit(favoriteMovies)
    }

    fun addFavoriteMovie(movie: MovieItem) {
        favoriteMovies.add(movie)
        println("Favorite movies after adding: ")
        favoriteMovies.onEach { println(it) }
    }

    fun removeFavoriteMovie(movie: MovieItem) {
        favoriteMovies.remove(movie)
        println("Favorite movies after removing: ")
        favoriteMovies.onEach { println(it) }
    }

}