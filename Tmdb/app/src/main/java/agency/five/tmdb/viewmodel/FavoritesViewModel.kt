package agency.five.tmdb.viewmodel

import agency.five.tmdb.remote.MovieResponse
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


    fun getFavoriteMovies(): Flow<List<MovieResponse>> {
        return repository.getFavoriteMovies()
    }

    fun addFavoriteMovie(movie: MovieResponse) {
        repository.addFavoriteMovie(movie)
    }

    fun removeFavoriteMovie(movie: MovieResponse) {
        repository.removeFavoriteMovie(movie)
    }

}


