package agency.five.tmdb.home

import agency.five.tmdb.home.MovieItem
import agency.five.tmdb.home.MovieResponse

interface MovieApi {

    suspend fun getPopularMovies(): MovieResponse
    suspend fun getStreamingMovies(): MovieResponse
    fun getOnTvMovies(): MovieResponse
    fun getForRentMovies(): MovieResponse
    fun getInTheatersMovies(): MovieResponse
    fun getMoviesCategory(): MovieResponse
    fun getTvMovies(): MovieResponse
    fun getTodayMovies(): MovieResponse
    fun getThisWeekMovies(): MovieResponse
}

internal class MovieApiImpl : MovieApi {

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
                imageUrl = "https://imdb-api.com/images/original/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_Ratio0.7273_AL_.jpg",
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
                imageUrl = "https://imdb-api.com/images/original/MV5BMTMxOGM0NzItM2E0OS00YWYzLWEzNzUtODUzZTBjM2I4MTZkXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_Ratio0.6716_AL_.jpg",
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

    private var inTheatersMovies =
        listOf(
            MovieItem(
                id = 1,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://imdb-api.com/images/original/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_Ratio0.7313_AL_.jpg",
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

    override suspend fun getPopularMovies(): MovieResponse = MovieResponse(
        movieItems
    )

    override suspend fun getStreamingMovies(): MovieResponse = MovieResponse(
        streamingMovies
    )

    override fun getOnTvMovies(): MovieResponse = MovieResponse(onTvMovies)

    override fun getForRentMovies(): MovieResponse = MovieResponse(forRentMovies)

    override fun getInTheatersMovies(): MovieResponse = MovieResponse(inTheatersMovies)

    override fun getMoviesCategory(): MovieResponse = MovieResponse(movieItems)

    override fun getTvMovies(): MovieResponse = MovieResponse(movieItems)

    override fun getTodayMovies(): MovieResponse = MovieResponse(movieItems)

    override fun getThisWeekMovies(): MovieResponse = MovieResponse(movieItems)


}