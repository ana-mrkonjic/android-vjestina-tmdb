package agency.five.tmdb.home

import agency.five.tmdb.home.MovieApi
import agency.five.tmdb.favorites.MovieDatabase
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

interface MovieRepository {

    fun getPopularMovies(): Flow<List<MovieItem>>
    fun getFavoriteMovies(): Flow<List<MovieItem>>
    fun addFavoriteMovie(movie: MovieItem)
    fun removeFavoriteMovie(movie: MovieItem)
    fun getStreamingMovies(): Flow<List<MovieItem>>
    fun getOnTvMovies(): Flow<List<MovieItem>>
    fun getForRentMovies(): Flow<List<MovieItem>>
    fun getInTheatersMovies(): Flow<List<MovieItem>>
    fun getMoviesCategory(): Flow<List<MovieItem>>
    fun getTvMovies(): Flow<List<MovieItem>>
    fun getTodayMovies(): Flow<List<MovieItem>>
    fun getThisWeekMovies(): Flow<List<MovieItem>>
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi, private val db: MovieDatabase
) : MovieRepository {


    override fun getPopularMovies(): Flow<List<MovieItem>> = flow {
        //val movies = db.getMoviesDb()
        //before updating movies, emit already cached movies
        //emit(movies)

        var remoteMovies: List<MovieItem> = emptyList<MovieItem>()
        try {
            //replace cached movies with new ones, fetched from API
            remoteMovies = movieApi.getPopularMovies().movies
            //db.deleteMovies(remoteMovies)
            //db.insertMovies(remoteMovies)
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }

        emit(remoteMovies)
        //val newMovies = db.getMoviesDb()
        //emit(newMovies)


    }

    override fun getFavoriteMovies(): Flow<List<MovieItem>> {
        return flow {
            val favoriteMovies = db.getFavoriteMoviesDb()
            emit(favoriteMovies)
        }


    }


    override fun addFavoriteMovie(movie: MovieItem) {
        db.addFavoriteMovieDb(movie)
    }

    override fun removeFavoriteMovie(movie: MovieItem) {
        db.removeFavoriteMovie(movie)
    }

    override fun getStreamingMovies(): Flow<List<MovieItem>> {
        return flow {
            val streamingMovies = movieApi.getStreamingMovies().movies
            emit(streamingMovies)
        }
    }

    override fun getOnTvMovies(): Flow<List<MovieItem>> {
        return flow {
            val onTvMovies = movieApi.getStreamingMovies().movies
            emit(onTvMovies)
        }
    }

    override fun getForRentMovies(): Flow<List<MovieItem>> {
        return flow {
            val forRentMovies = movieApi.getForRentMovies().movies
            emit(forRentMovies)
        }
    }

    override fun getInTheatersMovies(): Flow<List<MovieItem>> {
        return flow {
            val inTheatreMovies = movieApi.getForRentMovies().movies
            emit(inTheatreMovies)
        }
    }

    override fun getMoviesCategory(): Flow<List<MovieItem>> {
        return flow {
            val movies = movieApi.getMoviesCategory().movies
            emit(movies)
        }
    }

    override fun getTvMovies(): Flow<List<MovieItem>> {
        return flow {
            val tvMovies = movieApi.getTvMovies().movies
            emit(tvMovies)
        }
    }

    override fun getTodayMovies(): Flow<List<MovieItem>> {
        return flow {
            val movies = movieApi.getTodayMovies().movies
            emit(movies)
        }
    }

    override fun getThisWeekMovies(): Flow<List<MovieItem>> {
        return flow {
            val movies = movieApi.getThisWeekMovies().movies
            emit(movies)
        }
    }


}