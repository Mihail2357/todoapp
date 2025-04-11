package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.todolistitems.TodoListScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.navigation.Routes
import com.example.myapplication.todolistitems.EditTodoScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val navController = rememberNavController()

      MyApplicationTheme {
        NavHost(
          navController = navController,
          startDestination = Routes.TODO_LIST
        ) {
          composable(Routes.TODO_LIST) {
            TodoListScreen(
              onNavigate = { id ->
                navController.navigate(Routes.ADD_EDIT_TODO + "?todoId=${id}")
              }
            )
          }
          composable(
            route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
            arguments = listOf(
              navArgument(name = "todoId") {
                type = NavType.IntType
                defaultValue = -1
              }
            )
          ) {
            EditTodoScreen(onBack = {
              navController.popBackStack()
            })
          }
        }
      }
    }
  }
}