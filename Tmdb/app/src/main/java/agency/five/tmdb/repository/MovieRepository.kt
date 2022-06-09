package agency.five.tmdb.repository

import agency.five.tmdb.api.MovieApi
import agency.five.tmdb.database.DetailsItem
import agency.five.tmdb.database.MovieDatabase
import agency.five.tmdb.remote.DetailsResponse
import agency.five.tmdb.remote.MembersResponse
import agency.five.tmdb.remote.MovieResponse
import agency.five.tmdb.remote.MoviesResponse
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import java.io.IOException

interface MovieRepository {

    fun getPopularMovies(): Flow<MoviesResponse>
    fun getFavoriteMovies(): Flow<List<MovieResponse>>
    fun addFavoriteMovie(movie: MovieResponse)
    fun removeFavoriteMovie(movie: MovieResponse)


    fun getStreamingMovies(): Flow<MoviesResponse>
    fun getOnTvMovies(): Flow<MoviesResponse>
    fun getForRentMovies(): Flow<MoviesResponse>
    fun getInTheatersMovies(): Flow<MoviesResponse>
    fun getMoviesCategory(): Flow<MoviesResponse>
    fun getTvMovies():Flow<MoviesResponse>
    fun getTodayMovies():Flow<MoviesResponse>
    fun getThisWeekMovies(): Flow<MoviesResponse>
    fun getMovieById(id: Int): Flow<DetailsResponse?>
    fun getMovieCast(movieId: Int): Flow<MembersResponse?>
}

internal class MovieRepositoryImpl(
    private val movieApi: MovieApi, private val movieDatabase: MovieDatabase
) : MovieRepository {


    override fun getPopularMovies(): Flow<MoviesResponse> = flow {
        //val movies = db.getMoviesDb()
        //before updating movies, emit already cached movies
        //emit(movies)

        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            //replace cached movies with new ones, fetched from API
            remoteMovies = movieApi.getPopularMovies()?: MoviesResponse(emptyList())
            //db.deleteMovies(remoteMovies)
            //db.insertMovies(remoteMovies)
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }

        emit(remoteMovies)

    }

    override fun getOnTvMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getOnTvMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getForRentMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getForRentMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getInTheatersMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getInTheatersMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getMoviesCategory(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getMoviesCategory()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getTvMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getOnTvMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getTodayMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getTodayMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getThisWeekMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getThisWeekMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

    override fun getMovieById(id: Int): Flow<DetailsResponse?> = flow {
        val movie = movieApi.getMovieById(id)
        emit(movie)
    }

    override fun getMovieCast(movieId: Int): Flow<MembersResponse?> = flow {
        val cast =  movieApi.getMovieCast(movieId)
        if (cast != null) {
            for(c in cast.crew) {
                println(c.name)
            }
        }
        emit(cast)
    }


    override fun getFavoriteMovies(): Flow<List<MovieResponse>> {
        return movieDatabase.getFavoriteMovies()
    }


    override fun addFavoriteMovie(movie: MovieResponse) {
        movieDatabase.addFavoriteMovie(movie)
    }

    override fun removeFavoriteMovie(movie: MovieResponse) {
        movieDatabase.removeFavoriteMovie(movie)
    }

    override fun getStreamingMovies(): Flow<MoviesResponse> = flow {
        var remoteMovies: MoviesResponse = MoviesResponse(emptyList())
        try {
            remoteMovies = movieApi.getStreamingMovies()?: MoviesResponse(emptyList())
        } catch (e: HttpException) {
            //display error
        } catch (e: IOException) {
            //emit message check internet connection
        }
        emit(remoteMovies)

    }

/*
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
*/


}