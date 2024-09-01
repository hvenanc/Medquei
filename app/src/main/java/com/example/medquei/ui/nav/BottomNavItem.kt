package com.example.medquei.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    data object HomePage : BottomNavItem("Home", Icons.Default.Home, "home")
    data object CalendarPage : BottomNavItem("Calendário", Icons.Default.DateRange, "calendar")
    data object UserPage: BottomNavItem("Perfil", Icons.Default.Person, "user")

}