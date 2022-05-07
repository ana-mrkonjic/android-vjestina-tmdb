package agency.five.tmdb.di

import agency.five.tmdb.database.MovieDatabase
import agency.five.tmdb.api.MovieApi
import agency.five.tmdb.api.MovieApiImpl
import agency.five.tmdb.repository.MovieRepository
import agency.five.tmdb.repository.MovieRepositoryImpl
import agency.five.tmdb.viewmodel.DetailsViewModel
import agency.five.tmdb.viewmodel.FavoritesViewModel
import agency.five.tmdb.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databasesModule = module {

    single<MovieDatabase> {
        MovieDatabase()
    }
}

val apisModule = module {

    single<MovieApi> {
        MovieApiImpl()
    }
}

val reposModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(
            movieApi = get<MovieApi>(),
            movieDatabase = get<MovieDatabase>()
        )
    }
}

val viewModelsModule = module {

    viewModel {
        HomeViewModel(get())
    }

    viewModel { params ->
        DetailsViewModel(movieId = params.get(), repository = get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

}


