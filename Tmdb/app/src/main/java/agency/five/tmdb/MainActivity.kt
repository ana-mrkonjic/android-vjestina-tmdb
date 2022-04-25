package agency.five.tmdb


import agency.five.tmdb.di.detailsModule
import agency.five.tmdb.di.favoriteMoviesModule
import agency.five.tmdb.di.moviesModule
import agency.five.tmdb.navigation.MainScreen
import agency.five.tmdb.navigation.NavBarItems
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.koin.core.context.GlobalContext.startKoin


class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {

                startKoin {
                    modules(moviesModule, favoriteMoviesModule, detailsModule)
                }

                MainScreen()

            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val selectedIndex = remember { mutableStateOf(0) }
    val selectedItem = remember { mutableStateOf(false) }


    BottomNavigation(elevation = 10.dp, backgroundColor = Color.White) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    selectedItem.value = currentRoute == navItem.route
                    selectedIndex.value = navItem.index
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    navItem.image?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = navItem.title /*tint = if (selectedItem.value) colorResource(id = R.color.black) else colorResource(id = R.color.grey)*/
                        )

                    }
                },
                label = {
                    Text(text = navItem.title/*, color = if (selectedItem.value) colorResource(id = R.color.black) else colorResource(id = R.color.grey)*/)
                },
                selectedContentColor = colorResource(id = R.color.black),
                unselectedContentColor = colorResource(id = R.color.grey)

            )
        }
    }
}

