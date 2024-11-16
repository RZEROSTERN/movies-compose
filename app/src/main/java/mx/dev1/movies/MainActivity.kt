package mx.dev1.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mx.dev1.movies.navigation.MainNavigationBar
import mx.dev1.movies.navigation.MainNavigationItem
import mx.dev1.movies.navigation.Routes
import mx.dev1.movies.presentation.detail.DetailsScreen
import mx.dev1.movies.presentation.home.MoviesListScreen
import mx.dev1.movies.ui.theme.MoviesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = backStackEntry?.destination

                Scaffold (
                    bottomBar = {
                        AnimatedVisibility(
                            visible = currentDestination?.route?.contains(Routes.DetailsScreen) == false
                        ) {
                            MainNavigationBar(
                                navController = navController,
                                currentDestination = currentDestination
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HomeScreen
                    ) {
                        composable(route = Routes.HomeScreen) {
                            MoviesListScreen(
                                onMovieClick = { movie ->
                                    navController.navigate(Routes.DetailsScreen + "/${movie.id}")
                                }
                            )
                        }
                        composable(route = Routes.FavoritesScreen) {

                        }
                        composable(route = Routes.NowPlayingScreen) {

                        }
                        composable(
                            route = Routes.DetailsScreen + "/{movieId}",
                            arguments = listOf(
                                navArgument(name = "movieId") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val movieId = it.arguments?.getString("movieId")
                            DetailsScreen(
                                movieId = movieId
                            )
                        }
                    }
                }

            }
        }
    }
}
