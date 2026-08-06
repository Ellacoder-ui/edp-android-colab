package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeScreen(onShowGreeting = { typedName ->
                navController.navigate(Greeting(userName = typedName))
            })
        }

        composable<Greeting> { backStackEntry ->
            val greeting: Greeting = backStackEntry.toRoute()
            GreetingScreen(userName = greeting.userName)
        }
    }
}
