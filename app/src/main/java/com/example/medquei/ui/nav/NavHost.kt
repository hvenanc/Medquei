package com.example.medquei.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medquei.ui.theme.CalendarPage
import com.example.medquei.ui.theme.HomePage
import com.example.medquei.ui.theme.UserPage

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController, BottomNavItem.UserPage.route) {

        composable(route = BottomNavItem.HomePage.route) {
            HomePage()
        }
        composable(route = BottomNavItem.UserPage.route) {
            UserPage()
        }
        composable(route = BottomNavItem.CalendarPage.route) {
            CalendarPage()
        }
    }
}