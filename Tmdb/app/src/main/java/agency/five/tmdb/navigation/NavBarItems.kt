package agency.five.tmdb.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = "home",
            index = 0
        ),
        BarItem(
            title = "Favorites",
            image = Icons.Filled.Favorite,
            route = "favourites",
            index = 1
        )
    )
}