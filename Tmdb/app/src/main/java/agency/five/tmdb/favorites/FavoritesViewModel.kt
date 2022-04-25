package agency.five.tmdb.favorites

import agency.five.tmdb.home.MovieItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class FavoritesViewModel(
    //private val repository: MovieRepository
) : ViewModel(), KoinComponent {

    //val repository: MovieRepository = get()

    private var favoriteMovieItems =
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
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
            ),
            MovieItem(
                id = 3,
                title = "Title 3",
                overview = "Content 3",
                imageUrl = "https://resizing.flixster.com/CzGSVjJeg8yFD-sttP3mkLSVuxw=/206x305/v2/https://flxt.tmsimg.com/assets/p10182728_b_v13_bd.jpg",
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

    private val _favoriteMovies = MutableStateFlow(favoriteMovieItems)
    private val favoriteMovies =  _favoriteMovies.asStateFlow()

    var items: List<MovieItem> = emptyList()

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            //repository.getFavoriteMovies().collect() { it
            favoriteMovies.collect{
                items = it
            }
        }
    }


/*    fun addFavoriteMovie(movie: MovieItem) {

        val tempList = _favoriteMovies.value.toMutableList()
        tempList.add(movie)
        _favoriteMovies.value = tempList

        //val newList = _favoriteMovies + movie
        //_favoriteMovies.value = newList
    }*/




}


