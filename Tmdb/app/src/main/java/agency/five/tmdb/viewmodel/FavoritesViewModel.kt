package agency.five.tmdb.viewmodel

import agency.five.tmdb.repository.MovieItem
import agency.five.tmdb.repository.MovieRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: MovieRepository
) : ViewModel(){


    fun getFavoriteMovies(): Flow<List<MovieItem>> {
        return repository.getFavoriteMovies()
    }

    fun addFavoriteMovie(movie: MovieItem) {
        repository.addFavoriteMovie(movie)
    }

    fun removeFavoriteMovie(movie: MovieItem) {
        repository.removeFavoriteMovie(movie)
    }

}


