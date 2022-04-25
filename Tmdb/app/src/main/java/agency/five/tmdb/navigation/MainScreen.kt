package agency.five.tmdb.navigation

import agency.five.tmdb.BottomNavigationBar
import agency.five.tmdb.favorites.FavoritesScreen
import agency.five.tmdb.favorites.FavoritesViewModel
import agency.five.tmdb.home.HomeScreen
import agency.five.tmdb.home.HomeViewModel
import agency.five.tmdb.home.MyTopAppBar
import agency.five.tmdb.movieDetails.DetailsScreen
import agency.five.tmdb.movieDetails.DetailsViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel


@ExperimentalMaterialApi
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = 16.dp
            ) {
                MyTopAppBar()
            }
        },
        content = { NavigationHost(navController = navController) },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )

}

@ExperimentalMaterialApi
@Composable
fun NavigationHost(navController: NavHostController) {

    val vModel = getViewModel<HomeViewModel>()
    val fModel = getViewModel<FavoritesViewModel>()
    val dModel = getViewModel<DetailsViewModel>()


    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {

        composable(NavRoutes.Home.route) {
            HomeScreen(vModel, navController)
        }

        composable(NavRoutes.Favorites.route) {
            FavoritesScreen(fModel)
        }

        composable(NavRoutes.Details.route) {
            DetailsScreen(dModel)
        }
    }
}