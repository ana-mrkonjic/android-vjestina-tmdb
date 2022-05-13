package agency.five.tmdb.ui

import agency.five.tmdb.navigation.*

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@ExperimentalMaterialApi
@Composable
fun MainScreen(onMovieCardClick: (Int) -> Unit, rootNavHostController: NavHostController) {


    val bottomBarNavHostController = rememberNavController()

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
        content = {
            BottomBarNavigationGraph(
                rootNavHostController = rootNavHostController,
                bottomBarNavHostController = bottomBarNavHostController,
                onMovieCardClick = onMovieCardClick,
            )
        },
        bottomBar = { BottomNavigationBar(bottomBarNavHostController = bottomBarNavHostController) }
    )

}

/*
@ExperimentalMaterialApi
@Composable
fun NavigationHost(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {

        composable(NavRoutes.Home.route) {
            HomeScreen(navController)
        }

        composable(NavRoutes.Favorites.route) {
            FavoritesScreen(rootNavHostController)
        }

        composable(NavRoutes.Details.route) {
            DetailsScreen(
                rootNavHostController,
                entry.arguments?.getInt(RootScreen.Details.ARGUMENT_ID)
            )
        }
    }
}*/
