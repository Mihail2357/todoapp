package com.example.myapplication.todolistitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EditTodoScreen(
  onBack: () -> Unit,
  viewModel: TodoListViewModel = viewModel()
) {
  Scaffold(
    topBar = {
      TopBar(onBack)
    },
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    floatingActionButton = {
      FloatingActionButton(onClick = {
      }) {
        Icon(
          imageVector = Icons.Default.Check,
          contentDescription = "Save"
        )
      }
    }
  ) { paddingValues ->
    Column(
      modifier = Modifier.fillMaxSize().padding(paddingValues),
    ) {
      TextField(
        value = "",
        onValueChange = {
        },
        placeholder = {
          Text(text = "Title")
        },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(8.dp))
      TextField(
        value ="",
        onValueChange = {

        },
        placeholder = {
          Text(text = "Description")
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = false,
        maxLines = 5
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBack: () -> Unit) {
  TopAppBar(
    title = { Text(text = "Edit") },
    navigationIcon = {
      IconButton(onClick = onBack) {
        Icon(
          imageVector = Icons.Default.Close,
          contentDescription = "Save"
        )
      }
    }
  )
}