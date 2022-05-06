package agency.five.tmdb.navigation

import agency.five.tmdb.ui.DetailsScreen
import agency.five.tmdb.ui.MainScreen
import agency.five.tmdb.ui.HomeScreen
import agency.five.tmdb.ui.FavoritesScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@OptIn(
    ExperimentalFoundationApi::class,
    androidx.compose.material.ExperimentalMaterialApi::class
)
@Composable
fun RootNavigationGraph(
    rootNavHostController: NavHostController
) {
    NavHost(rootNavHostController, startDestination = RootScreen.Main.route) {
        composable(RootScreen.Main.route) {
            MainScreen(
                onMovieCardClick = { movieId ->
                    rootNavHostController.navigate(RootScreen.Details.route + "/${movieId}")
                },
                rootNavHostController
            )
        }
        composable(
            route = RootScreen.Details.route + "/{${RootScreen.Details.ARGUMENT_ID}}",
            arguments = listOf(navArgument(RootScreen.Details.ARGUMENT_ID) {
                type = NavType.IntType
            })
        ) { entry ->
            val id = entry.arguments?.getInt(RootScreen.Details.ARGUMENT_ID)!!

            DetailsScreen(
                movieId = id,
                navigateUp = {
                    rootNavHostController.popBackStack(
                        RootScreen.Details.route + "/{${RootScreen.Details.ARGUMENT_ID}}",
                        inclusive = true
                    )
                }
            )

        }
    }
}

sealed class RootScreen(
    val route: String,
    val title: String
) {
    object Main : RootScreen(
        route = "main",
        title = "Main"
    )

    object Details : RootScreen(
        route = "details",
        title = "Details"
    ) {

        const val ARGUMENT_ID = "id"
    }
}

@OptIn(
    ExperimentalFoundationApi::class,
    androidx.compose.material.ExperimentalMaterialApi::class
)
@Composable
fun BottomBarNavigationGraph(
    rootNavHostController: NavHostController,
    bottomBarNavHostController: NavHostController,
    onMovieCardClick: (Int) -> Unit
) {
    NavHost(bottomBarNavHostController, startDestination = BottomBarScreen.Home.route) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(onMovieCardClick)
        }
        composable(BottomBarScreen.Favourites.route) {
            FavoritesScreen(
                onFavoriteClick = { movieId ->
                    rootNavHostController.navigate(RootScreen.Details.route + "/${movieId}")
                },
            )
        }
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object Favourites : BottomBarScreen(
        route = "favourites",
        title = "Favourites",
        icon = Icons.Filled.Favorite
    )
}