package agency.five.tmdb.api

import agency.five.tmdb.remote.DetailsResponse
import agency.five.tmdb.remote.HttpRoutes.API_KEY
import agency.five.tmdb.remote.MembersResponse
import agency.five.tmdb.remote.MoviesResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

interface MovieApi {

    suspend fun getPopularMovies(): MoviesResponse?


    suspend fun getStreamingMovies(): MoviesResponse?
    suspend fun getOnTvMovies(): MoviesResponse?
    suspend fun getForRentMovies(): MoviesResponse?
    suspend fun getInTheatersMovies(): MoviesResponse?
    suspend fun getMoviesCategory(): MoviesResponse?
    suspend fun getTvMovies(): MoviesResponse?
    suspend fun getTodayMovies(): MoviesResponse?
    suspend fun getThisWeekMovies(): MoviesResponse?
    suspend fun getMovieById(id: Int): DetailsResponse?
    suspend fun getMovieCast(id: Int): MembersResponse?
}

internal class MovieApiImpl(
    private val client: HttpClient
) : MovieApi {

    override suspend fun getMovieById(id: Int): DetailsResponse? {

        return try {
            client.get {
                url("https://api.themoviedb.org/3/movie/$id?api_key=$API_KEY")
            }
        } catch(e: RedirectResponseException) {
                // 3xx - responses
                println("Error: ${e.response.status.description}")
                return null
            } catch(e: ClientRequestException) {
                // 4xx - responses
                println("Error: ${e.response.status.description}")
                null
            } catch(e: ServerResponseException) {
                // 5xx - responses
                println("Error: ${e.response.status.description}")
                null
            } catch(e: Exception) {
                println("Error: ${e.message}")
                null
            }

    }

    override suspend fun getMovieCast(id: Int): MembersResponse? {
        return try {
            client.get {
                url("https://api.themoviedb.org/3/movie/$id/credits?api_key=$API_KEY")
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            return null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }

    }

    override suspend fun getPopularMovies(): MoviesResponse? {



        return try {
            client.get {
                url("https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY")
            }


        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            return null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

   override suspend fun getStreamingMovies(): MoviesResponse? {



       return try {
           client.get {
               url("https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY")
           }
       } catch(e: RedirectResponseException) {
           // 3xx - responses
           println("Error: ${e.response.status.description}")
           return null
       } catch(e: ClientRequestException) {
           // 4xx - responses
           println("Error: ${e.response.status.description}")
           null
       } catch(e: ServerResponseException) {
           // 5xx - responses
           println("Error: ${e.response.status.description}")
           null
       } catch(e: Exception) {
           println("Error: ${e.message}")
           null
       }
   }

        override suspend fun getOnTvMovies(): MoviesResponse? {

            return try {
                client.get {
                    url("https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY")//url(" https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY")
                }
            } catch(e: RedirectResponseException) {
                // 3xx - responses
                println("Error: ${e.response.status.description}")
                return null
            } catch(e: ClientRequestException) {
                // 4xx - responses
                println("Error: ${e.response.status.description}")
                null
            } catch(e: ServerResponseException) {
                // 5xx - responses
                println("Error: ${e.response.status.description}")
                null
            } catch(e: Exception) {
                println("Error: ${e.message}")
                null
            }
        }

       override suspend fun getForRentMovies(): MoviesResponse? {

           return try {
               client.get {
                   url("https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY")
               }
           } catch(e: RedirectResponseException) {
               // 3xx - responses
               println("Error: ${e.response.status.description}")
               return null
           } catch(e: ClientRequestException) {
               // 4xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: ServerResponseException) {
               // 5xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: Exception) {
               println("Error: ${e.message}")
               null
           }
       }

       override suspend fun getInTheatersMovies(): MoviesResponse? {

           return try {
               client.get {
                   url("https://api.themoviedb.org/3/movie/upcoming?api_key=$API_KEY")
               }
           } catch(e: RedirectResponseException) {
               // 3xx - responses
               println("Error: ${e.response.status.description}")
               return null
           } catch(e: ClientRequestException) {
               // 4xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: ServerResponseException) {
               // 5xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: Exception) {
               println("Error: ${e.message}")
               null
           }
       }

       override suspend fun getMoviesCategory(): MoviesResponse? {

           return try {
               client.get {
                   url("https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY")
               }
           } catch(e: RedirectResponseException) {
               // 3xx - responses
               println("Error: ${e.response.status.description}")
               return null
           } catch(e: ClientRequestException) {
               // 4xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: ServerResponseException) {
               // 5xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: Exception) {
               println("Error: ${e.message}")
               null
           }
       }


    override suspend fun getTvMovies(): MoviesResponse? {

        return try {
            client.get {
                url(" https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY")
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            return null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

       override suspend fun getTodayMovies(): MoviesResponse? {

        return try {
            client.get {
                url("https://api.themoviedb.org/3/movie/top_rated?api_key=$API_KEY")
            }
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            return null
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch(e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

       override suspend fun getThisWeekMovies(): MoviesResponse? {

           return try {
               client.get {
                   url(" https://api.themoviedb.org/3/movie/now_playing?api_key=$API_KEY")
               }
           } catch(e: RedirectResponseException) {
               // 3xx - responses
               println("Error: ${e.response.status.description}")
               return null
           } catch(e: ClientRequestException) {
               // 4xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: ServerResponseException) {
               // 5xx - responses
               println("Error: ${e.response.status.description}")
               null
           } catch(e: Exception) {
               println("Error: ${e.message}")
               null
           }
       }


}