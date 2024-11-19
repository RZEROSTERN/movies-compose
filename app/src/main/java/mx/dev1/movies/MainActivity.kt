package mx.dev1.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import mx.dev1.movies.navigation.MainNavigationBar
import mx.dev1.movies.navigation.Routes
import mx.dev1.movies.presentation.detail.DetailsScreen
import mx.dev1.movies.presentation.detail.MovieDetailsViewModel
import mx.dev1.movies.presentation.favorites.FavoriteMoviesScreen
import mx.dev1.movies.presentation.home.MoviesListScreen
import mx.dev1.movies.presentation.home.MoviesListViewModel
import mx.dev1.movies.ui.theme.MoviesTheme

@AndroidEntryPoint
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
                ) { _ ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HomeScreen
                    ) {
                        composable(route = Routes.HomeScreen) {
                            MoviesListScreen(
                                onMovieClick = { movie ->
                                    navController.navigate(Routes.DetailsScreen + "/${movie.id}")
                                },
                                viewModel = hiltViewModel<MoviesListViewModel>()
                            )
                        }
                        composable(route = Routes.FavoritesScreen) {
                            FavoriteMoviesScreen(
                                navigateToDetails = { movie ->
                                    navController.navigate(Routes.FavoritesScreen)
                                }
                            )
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

                            if (movieId != null) {
                                DetailsScreen(
                                    movieId = movieId,
                                    viewModel = hiltViewModel<MovieDetailsViewModel>(),
                                    onBack = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
