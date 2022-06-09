package agency.five.tmdb.viewmodel

import agency.five.tmdb.database.DetailsItem
import agency.five.tmdb.remote.DetailsResponse
import agency.five.tmdb.remote.MembersResponse
import agency.five.tmdb.repository.MovieRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class DetailsViewModel(
    private val movieId: Int,
    private val repository: MovieRepository
) : ViewModel() {

    fun getMovie(): Flow<DetailsResponse?> {
        return repository.getMovieById(movieId)
    }

    fun getCast(movieId: Int): Flow<MembersResponse?> {
        return repository.getMovieCast(movieId)
    }
}