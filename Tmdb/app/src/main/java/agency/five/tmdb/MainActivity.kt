package agency.five.tmdb

import agency.five.tmdb.navigation.RootNavigationGraph
import agency.five.tmdb.ui.MainScreen
import agency.five.tmdb.ui.theme.TmdbTheme
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbTheme {
                val rootNavHostController = rememberNavController()
                //MainScreen(rootNavHostController)
                Surface {
                    RootNavigationGraph(rootNavHostController = rootNavHostController)
                }

            }
        }
    }
}


