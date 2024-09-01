package com.example.medquei.ui.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.HomePage,
        BottomNavItem.CalendarPage,
        BottomNavItem.UserPage,
    )
    NavigationBar(
        containerColor = Color(0xFFE9F6FE)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        items.forEach{item -> 
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title, tint = Color(0xFF125451))},
                label = {Text(text = item.title, fontSize = 12.sp, color = Color(0xFF125451))},
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                            restoreState = true
                        }
                        launchSingleTop =  true
                    }
                }
            )
        }
    }
}