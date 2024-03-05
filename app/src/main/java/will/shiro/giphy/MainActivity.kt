package will.shiro.giphy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import will.shiro.giphy.gifs.details.GifDetailsScreen
import will.shiro.giphy.gifs.home.GifHomeScreen
import will.shiro.giphy.gifs.search.GifSearchScreen
import will.shiro.giphy.theme.GifsListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GifsListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "gifHome",
                        enterTransition = {
                            EnterTransition.None
                        },
                        exitTransition = {
                            ExitTransition.None
                        }
                    ) {
                        composable("gifHome") {
                            GifHomeScreen(onSearchTextClick = {
                                navController.navigate("gifSearch")
                            })
                        }
                        composable("gifSearch") {
                            GifSearchScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onGifClick = {
                                    navController.navigate("gifDetails")
                                })
                        }
                        composable("gifDetails") {
                            GifDetailsScreen(onBackClick = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }
}
