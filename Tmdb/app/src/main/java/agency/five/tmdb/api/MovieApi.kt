package agency.five.tmdb.api

import agency.five.tmdb.database.CastMember
import agency.five.tmdb.database.DetailsItem
import agency.five.tmdb.repository.MovieItem
import agency.five.tmdb.repository.MovieResponse

interface MovieApi {

    suspend fun getPopularMovies(): MovieResponse
    suspend fun getStreamingMovies(): MovieResponse
    suspend fun getOnTvMovies(): MovieResponse
    suspend fun getForRentMovies(): MovieResponse
    suspend fun getInTheatersMovies(): MovieResponse
    suspend fun getMoviesCategory(): MovieResponse
    suspend fun getTvMovies(): MovieResponse
    suspend fun getTodayMovies(): MovieResponse
    suspend fun getThisWeekMovies(): MovieResponse
    suspend fun getMovieById(id: Int): DetailsItem
}

internal class MovieApiImpl : MovieApi {

    val actors = listOf(
        CastMember("Leonardo DiCaprio", "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg", "Jack Dawson"),
        CastMember("Kate Winslet", "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg", "Rose De.")
    )

    val directors = listOf(
        CastMember("Don Heck", "", "Director"),
        CastMember("Jack Kirby", "", "Director")
    )

    private var detailedMovies =
        listOf(
            DetailsItem(
                id = 1,
                title = "Peaky Blinders",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
                score = 72.2,
                genre = "Drama",
                releaseDate = "1997-12-12",
                cast = actors,
                directors = directors
            ),
            DetailsItem(
                id = 2,
                title = "Inception",
                overview = "Content 2",
                imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
                score = 80.0,
                genre = "Action",
                releaseDate = "1997-12-12",
                cast = actors,
                directors = directors

                ),
            DetailsItem(
                id = 3,
                title = "Titanic",
                overview = "Movie about..",
                imageUrl = "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg",
                score = 80.0,
                genre = "Drama",
                releaseDate = "1997-12-12",
                cast = actors,
                directors = directors

            ),
            DetailsItem(
                id = 4,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
                score = 80.0,
                genre = "Drama",
                releaseDate = "1997-12-12",
                cast = actors,
                directors = directors

            )
        )

    private var movieItems =
        listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
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
                imageUrl = "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg",
            ),
            MovieItem(
                id = 4,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 5,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 6,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 7,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 8,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 9,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 10,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 11,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 12,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 13,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 14,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            )
        )


    private var streamingMovies =
        listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
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
                imageUrl = "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg",
            ),
            MovieItem(
                id = 4,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            )
        )

    private var onTvMovies =
        listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://imdb-api.com/images/original/MV5BMTMxOGM0NzItM2E0OS00YWYzLWEzNzUtODUzZTBjM2I4MTZkXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_Ratio0.6716_AL_.jpg",
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
                imageUrl = "https://m.media-amazon.com/images/M/MV5BYjUyN2VlZGEtNGEyZC00YjViLTgwYmQtZDJiM2FlOTU3Mjg2XkEyXkFqcGdeQXVyMjMxOTE0ODA@._V1_UX128_CR0,3,128,176_AL_.jpg",
            ),
            MovieItem(
                id = 4,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            )
        )

    private var forRentMovies =
        listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://imdb-api.com/images/original/MV5BNzhlY2E5NDUtYjJjYy00ODg3LWFkZWQtYTVmMzU4ZWZmOWJkXkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg",
            ),
            MovieItem(
                id = 2,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://imdb-api.com/images/original/MV5BYWVhN2Q2MDEtMzIzYS00M2EwLTlmZDItMzk5YTUzZWE0MjkwXkEyXkFqcGdeQXVyMTEzMTI1Mjk3._V1_Ratio0.6716_AL_.jpg",
            ),
            MovieItem(
                id = 3,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://imdb-api.com/images/original/MV5BODllNWE0MmEtYjUwZi00ZjY3LThmNmQtZjZlMjI2YTZjYmQ0XkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_Ratio0.7273_AL_.jpg",
            ),
            MovieItem(
                id = 4,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            )
        )

    private var inTheatersMovies =
        listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://imdb-api.com/images/original/MV5BYzZkOTY4MDgtODI5Mi00ZjA4LWJkODgtYzBiOGE3MWNhZWFmXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_Ratio0.7273_AL_.jpg",
            ),
            MovieItem(
                id = 2,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://imdb-api.com/images/original/MV5BYWVhN2Q2MDEtMzIzYS00M2EwLTlmZDItMzk5YTUzZWE0MjkwXkEyXkFqcGdeQXVyMTEzMTI1Mjk3._V1_Ratio0.6716_AL_.jpg",
            ),
            MovieItem(
                id = 3,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg",
            ),
            MovieItem(
                id = 4,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            )
        )

    override suspend fun getMovieById(id: Int): DetailsItem {

        for(movie in detailedMovies) {
            if(movie.id == id) {
                return movie
            }
        }
        return detailedMovies[3]
    }

    override suspend fun getPopularMovies(): MovieResponse = MovieResponse(
        movieItems
    )

    override suspend fun getStreamingMovies(): MovieResponse = MovieResponse(
        streamingMovies
    )

    override suspend fun getOnTvMovies(): MovieResponse = MovieResponse(onTvMovies)

    override suspend fun getForRentMovies(): MovieResponse = MovieResponse(forRentMovies)

    override suspend fun getInTheatersMovies(): MovieResponse = MovieResponse(inTheatersMovies)

    override suspend fun getMoviesCategory(): MovieResponse = MovieResponse(onTvMovies)

    override suspend fun getTvMovies(): MovieResponse = MovieResponse(movieItems)

    override suspend fun getTodayMovies(): MovieResponse = MovieResponse(movieItems)

    override suspend fun getThisWeekMovies(): MovieResponse = MovieResponse(inTheatersMovies)


}