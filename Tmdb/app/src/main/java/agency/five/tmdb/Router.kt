package agency.five.tmdb


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class Screen() {
    object MainScreen : Screen()
    object DetailsScreen : Screen()
    //object HomeScreen : Screen()
    //object FavoritesScreen : Screen()
}

object Router {
    var currentScreen: Screen by mutableStateOf(Screen.MainScreen)

    fun navigateTo(destination: Screen) {
        currentScreen = destination
    }
}
