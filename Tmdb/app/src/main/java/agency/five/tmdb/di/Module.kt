package agency.five.tmdb.di

import agency.five.tmdb.*

import agency.five.tmdb.favorites.FavoritesViewModel
import agency.five.tmdb.favorites.MovieDatabase
import agency.five.tmdb.home.HomeViewModel
import agency.five.tmdb.home.MovieApi
import agency.five.tmdb.home.MovieRepository
import agency.five.tmdb.home.MovieRepositoryImpl
import agency.five.tmdb.movieDetails.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repoModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            movieApi = get<MovieApi>(),
            db = get<MovieDatabase>()
        )
    }
}

val moviesModule = module {

    viewModel {
        HomeViewModel(/*get<MovieRepository>()*/)
    }

}

val detailsModule = module {

    viewModel {
        DetailsViewModel(/*get<MovieRepository>()*/)
    }

}

val favoriteMoviesModule = module {

    viewModel {
        FavoritesViewModel(/*get<MovieRepository>()*/)
    }

}