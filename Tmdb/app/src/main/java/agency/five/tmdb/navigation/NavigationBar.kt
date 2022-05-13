package agency.five.tmdb.navigation

import agency.five.tmdb.R
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
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

@Composable
fun BottomNavigationBar(
    bottomBarNavHostController: NavHostController
) {

    val selectedIndex = remember { mutableStateOf(0) }
    val selectedItem = remember { mutableStateOf(false) }


    BottomNavigation(elevation = 10.dp, backgroundColor = Color.White) {
        val backStackEntry by bottomBarNavHostController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    selectedItem.value = currentRoute == navItem.route
                    selectedIndex.value = navItem.index
                    bottomBarNavHostController.navigate(navItem.route) {
                        popUpTo(bottomBarNavHostController.graph.findStartDestination().id) {
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

