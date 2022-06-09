package agency.five.tmdb.viewmodel

import agency.five.tmdb.remote.MovieResponse
import agency.five.tmdb.remote.MoviesResponse
import agency.five.tmdb.repository.MovieItem
import agency.five.tmdb.repository.MovieRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val categoryMap = mapOf("What's popular" to listOf<String>("Streaming", "On TV", "For Rent"),
        "Free to Watch" to listOf("Movies", "TV"), "Trending" to listOf("Today", "This Week"))

    fun getCategories(category: String): Flow<List<String>> = flow {
        var categories: List<String> = emptyList<String>()
        categoryMap[category]
        emit(categories)
    }

    fun getTabs(category: String): List<String> {
         return categoryMap[category]!!

    }

    private val popularTabMovies = listOf(
        repository.getStreamingMovies(),
        repository.getOnTvMovies(),
        repository.getForRentMovies(),
    )

    private val freeToWatchTabMovies = listOf(
        repository.getMoviesCategory(),
        repository.getTvMovies(),
    )

    private val trendingTabMovies = listOf(
        repository.getTodayMovies(),
        repository.getThisWeekMovies(),
    )

    private val popularIndex = MutableStateFlow<Int>(0)
    private val freeIndex = MutableStateFlow<Int>(0)
    private val trendingIndex = MutableStateFlow<Int>(0)


    fun getWhatsPopular(): Flow<MovieCategoryViewState> = popularIndex.flatMapLatest { index ->

        popularTabMovies[index].map {
            MovieCategoryViewState(
                tabs = categoryMap["What's popular"]!!,
                selectedTabIndex = index,
                movies = it
            )
        }
    }

    fun getFreeToWatch(): Flow<MovieCategoryViewState> = freeIndex.flatMapLatest { index ->

        freeToWatchTabMovies[index].map {
            MovieCategoryViewState(
                tabs = categoryMap["Free to Watch"]!!,
                selectedTabIndex = index,
                movies = it
            )
        }

    }


    fun getTrending(): Flow<MovieCategoryViewState> = trendingIndex.flatMapLatest { index ->

        trendingTabMovies[index].map {
            MovieCategoryViewState(
                tabs = categoryMap["Trending"]!!,
                selectedTabIndex = index,
                movies = it
            )
        }

    }

    fun selectCategory(category: String, index:Int) {
        when(category) {
            "What's popular" -> selectWhatsPopular(index)
            "Free To Watch" -> selectFreeToWatch(index)
            "Trending" -> selectTrending(index)
        }
    }

    fun getCategoryMovies(category: String): Flow<MovieCategoryViewState> {
        return when(category) {
            "What's popular" -> getWhatsPopular()
            "Free To Watch" -> getFreeToWatch()
            "Trending" -> getTrending()
            else -> emptyFlow()
        }

    }


    private fun selectWhatsPopular(index: Int) {
        popularIndex.value = index
    }

    private fun selectFreeToWatch(index: Int) {
        freeIndex.value = index
    }

    private fun selectTrending(index: Int) {
        trendingIndex.value = index
    }

    suspend fun addFavoriteMovie(movie: MovieResponse) {
        repository.addFavoriteMovie(movie)
    }

    suspend fun removeFavoriteMovie(movie: MovieResponse) {
        repository.removeFavoriteMovie(movie)
    }
}

data class MovieCategoryViewState(
    val tabs: List<String>,
    val selectedTabIndex: Int,
    val movies: MoviesResponse,
)