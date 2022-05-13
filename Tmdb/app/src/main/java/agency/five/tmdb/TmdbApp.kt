package agency.five.tmdb;

import agency.five.tmdb.di.apisModule
import agency.five.tmdb.di.databasesModule
import agency.five.tmdb.di.reposModule
import agency.five.tmdb.di.viewModelsModule
import android.app.Application
import org.koin.core.context.startKoin

class TmdbApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelsModule, reposModule, databasesModule, apisModule)
        }
    }
}
