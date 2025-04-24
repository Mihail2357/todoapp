package com.example.myapplication.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    Log.d("SharedViewModel", "NavBackStackEntry: $this")
    val navGraphRoute = destination.parent?.startDestinationRoute ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    Log.d("SharedViewModel", "parentEntry $parentEntry")
    return viewModel(parentEntry)
}