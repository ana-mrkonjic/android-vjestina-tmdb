package agency.five.tmdb.viewmodel

import agency.five.tmdb.database.DetailsItem
import agency.five.tmdb.repository.MovieRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class DetailsViewModel(
    private val movieId: Int,
    private val repository: MovieRepository
) : ViewModel() {

    fun getMovie(): Flow<DetailsItem> {
        return repository.getMovieById(movieId)
    }

}