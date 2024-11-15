package mx.dev1.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.dev1.movies.presentation.detail.DetailsScreen
import mx.dev1.movies.presentation.home.MoviesListScreen
import mx.dev1.movies.ui.theme.MoviesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = "movieList"
                    ) {
                        composable(route = "movieList") {
                            MoviesListScreen(
                                onMovieClick = { movie ->
                                    navController.navigate("details")
                                }
                            )
                        }
                        composable(route = "details") {
                            DetailsScreen()
                        }
                    }
                }

            }
        }
    }
}
