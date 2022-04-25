package agency.five.tmdb.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class HomeViewModel(
    //private val repository: MovieRepository
) : ViewModel(), KoinComponent {

    //val repository: MovieRepository = get()



    private var popularList =
        mutableStateOf(
            listOf("Streaming", "On TV", "For Rent", "In theaters")
        )

    private var freeToWatchList =
        mutableStateOf(
            listOf("Movies", "TV")
        )

    private var trendingsList =
        mutableStateOf(
            listOf("Today", "This week")
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
                id = 11,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 22,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
            ),
            MovieItem(
                id = 33,
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
                id = 111,
                title = "Title 1",
                overview = "Content 1",
                imageUrl = "https://imdb-api.com/images/original/MV5BMTMxOGM0NzItM2E0OS00YWYzLWEzNzUtODUzZTBjM2I4MTZkXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_Ratio0.6716_AL_.jpg",
            ),
            MovieItem(
                id = 222,
                title = "Title 2",
                overview = "Content 2",
                imageUrl = "https://imdb-api.com/images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6751_AL_.jpg",
            ),
            MovieItem(
                id = 333,
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

     //var movies: State<MutableList<MovieItem>> = mutableStateOf(mutableListOf())
    //private val _movies = MutableStateFlow(movieItems)

     private val _movies = MutableStateFlow(movieItems)
     private val movies = _movies.asStateFlow()

    private val _streamingMovies = MutableStateFlow(streamingMovies)
    private val streamingMoviess = _streamingMovies.asStateFlow()

    private val _onTvMovies = MutableStateFlow(onTvMovies)
    private val onTvMoviess = _onTvMovies.asStateFlow()

    private val _forRentMovies = MutableStateFlow(forRentMovies)
    private val forRentMoviess = _forRentMovies.asStateFlow()

    private val _categoryMovies = MutableStateFlow(emptyList<MovieItem>())
    private val categoryyMovies = _categoryMovies.asStateFlow()

    //private var favoriteMovies: MutableList<MovieItem> = emptyList<MovieItem>().toMutableList()

/*    fun getFavoriteMovies(): MutableList<MovieItem> {

        for(i in favoriteMovies) println("Favorite " +i.id )
        return favoriteMovies
    }*/

//    fun addFavoriteMovie(movie: MovieItem) {
//        favoriteMovies.add(movie)
//    }
//
//    fun removeFavoriteMovie(movie: MovieItem) {
//        favoriteMovies.remove(movie)
//    }

    var movieList: List<MovieItem> = emptyList()
    var categorieList: List<MutableState<List<String>>> = emptyList()
    var categoryMovies: List<MovieItem> = emptyList()

    private val _categories = MutableStateFlow(listOf("Streaming", "On TV", "For Rent", "In theaters","Movies", "TV","Today", "This week"))
    private val categories = listOf("Streaming", "On TV", "For Rent", "In theaters","Movies", "TV","Today", "This week")//_categories.asStateFlow()

    init {
        getMovies()
       // getCategories()
        getCategoryMovies("")
    }

    private fun getMovies() {
        viewModelScope.launch {
            //repository.getPopularMovies().collect {
            movies.collect{
                println("novi ispis")
                println("The current: " + it)
                movieList = it
            }
        }
    }

   //val categoryMap = LinkedHashMap<String, List<String>>()
    //categoryMap["What's popular"] = listOf<String>("Streaming", "On TV", "For Rent")

    val categoryMap = mapOf("What's popular" to listOf<String>("Streaming", "On TV", "For Rent"),
        "Free to Watch" to listOf("Movies", "TV"), "Trending" to listOf("Today", "This Week"))

    fun getCategories(): List<String> {
        return categories
    }

    fun getCategoryMovies(category: String) {
/*        return when(category) {
            "Streaming" -> streamingMovies
            "On TV" -> onTvMovies
            "For Rent" -> forRentMovies
            "In theaters" -> inTheatersMovies
            "Movies" -> movieItems
            "TV" -> onTvMovies

            "Today" -> onTvMovies
            "This Week" -> onTvMovies
            else -> {
                emptyList<MovieItem>()
            }
        }*/
        viewModelScope.launch {
            when (category) {
                "Streaming" -> streamingMoviess.collect {
                    categoryMovies = it

                }
                "On TV" -> onTvMoviess.collect {
                    categoryMovies = it
                }
                "For Rent" -> forRentMoviess.collect {
                    categoryMovies = it
                }
                "In theaters" -> streamingMoviess.collect {
                    categoryMovies = it
                }
                "Movies" -> streamingMoviess.collect {
                    categoryMovies = it
                }
                "TV" -> streamingMoviess.collect {
                    categoryMovies = it
                }

                "Today" -> streamingMoviess.collect {
                    categoryMovies = it
                }
                "This Week" -> streamingMoviess.collect {
                    categoryMovies = it
                }
                else -> {
                    emptyList<MovieItem>()
                }
            }

        }
    }


 /*   fun addFavoriteMovie(movie: MovieItem) {
        repository.addFavoriteMovie(movie = movie)
    }

    fun removeFavoriteMovie(movie: MovieItem) {
        repository.removeFavoriteMovie(movie)
    }

    fun getFavoriteMovies(): Flow<List<MovieItem>> {
        return repository.getFavoriteMovies()
    }


    fun getCategoryMovies(category: String) {


        viewModelScope.launch {
            when (category) {
                "Streaming" -> repository.getStreamingMovies().collect{
                    categoryMovies = it
                }
                "On TV" -> repository.getOnTvMovies().collect{
                    categoryMovies = it
                }
                "For Rent" -> repository.getForRentMovies().collect{
                    categoryMovies = it
                }
                "In theaters" -> repository.getInTheatersMovies().collect{
                    categoryMovies = it
                }
                "Movies" -> repository.getMoviesCategory().collect{
                    categoryMovies = it
                }
                "TV" -> repository.getTvMovies().collect{
                    categoryMovies = it
                }

                "Today" -> repository.getTodayMovies().collect{
                    categoryMovies = it
                }
                "This Week" -> repository.getThisWeekMovies().collect{
                    categoryMovies = it
                }
                else -> {
                    emptyList<MovieItem>()
                }
            }

        }


    }
*/

}