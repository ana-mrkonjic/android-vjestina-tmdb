package agency.five.tmdb.di.ktor


import agency.five.tmdb.api.MovieApi
import agency.five.tmdb.api.MovieApiImpl
import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.*

class KtorClient {

        //fun createKtorClient(): MovieApi {

            val httpClient: HttpClient = HttpClient(Android) {

                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    })
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("HTTP", message)
                        }
                    }
                    level = LogLevel.ALL
                }
            }

            //return MovieApiImpl(httpClient)

        //}
    }
