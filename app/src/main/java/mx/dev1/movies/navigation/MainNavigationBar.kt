package mx.dev1.movies.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination

@Composable
fun MainNavigationBar(
    navController: NavController,
    currentDestination: NavDestination?
) {
    val navigationItems = listOf(
        MainNavigationItem(
            title = "Home",
            route = Routes.HomeScreen,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        MainNavigationItem(
            title = "Favorites",
            route = Routes.FavoritesScreen,
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        MainNavigationItem(
            title = "Now playing",
            route = Routes.NowPlayingScreen,
            selectedIcon = Icons.Filled.PlayArrow,
            unselectedIcon = Icons.Outlined.PlayArrow
        ),
    )
    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                label = {
                    Text(
                        text = item.title
                    )
                },
                selected = currentDestination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Routes.HomeScreen)
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination?.route == item.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}
