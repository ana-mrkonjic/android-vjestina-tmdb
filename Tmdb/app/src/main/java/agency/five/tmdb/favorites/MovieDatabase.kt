package agency.five.tmdb.favorites

import agency.five.tmdb.home.MovieItem


//dao
class MovieDatabase {


    private var db_favorite_movies =
        listOf(
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

    fun getFavoriteMoviesDb(): List<MovieItem> {
        return db_favorite_movies
    }

    fun addFavoriteMovieDb(movie: MovieItem) {
        db_favorite_movies + movie
        println("Favorite movies after adding: " )
        db_favorite_movies.onEach { println(it) }
    }

    fun removeFavoriteMovie(movie: MovieItem) {
        db_favorite_movies - movie
        println("Favorite movies after removing: " )
        db_favorite_movies.onEach { println(it) }
    }

}