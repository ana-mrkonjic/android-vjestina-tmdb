package agency.five.tmdb.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Details : NavRoutes("details")
    object Favorites : NavRoutes("favorites")
}
