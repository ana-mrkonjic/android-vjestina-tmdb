package agency.five.tmdb;

import agency.five.tmdb.di.*
import android.app.Application
import org.koin.core.context.startKoin

class TmdbApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelsModule, reposModule, databasesModule, apisModule, clientModule)
        }
    }
}
