package com.example.medquei.ui.nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medquei.MainViewModel
import com.example.medquei.db.fb.FBDatabase
import com.example.medquei.ui.pages.HomePage
import com.example.medquei.ui.pages.CalendarPage
import com.example.medquei.ui.pages.UserPage

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    context: Context,
    fbDB : FBDatabase
) {
    NavHost(navController, BottomNavItem.HomePage.route) {

        composable(route = BottomNavItem.HomePage.route) {
            HomePage(context = context, viewModel = viewModel, fbDB = fbDB)
        }
        composable(route = BottomNavItem.UserPage.route) {
            UserPage(context = context, viewModel = viewModel, fbDB = fbDB)
        }
        composable(route = BottomNavItem.CalendarPage.route) {
            CalendarPage(context = context, viewModel = viewModel, fbDB = fbDB)
        }
    }
}

