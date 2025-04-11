package com.example.myapplication.todolistitems

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoListScreen(
  onNavigate: (Int) -> Unit,
  viewModel: TodoListViewModel = viewModel()
) {
  val uiState: TodoListUiState by viewModel.uiState.collectAsStateWithLifecycle()

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(onClick = viewModel::onAddItem) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Add"
        )
      }
    }
  ) { paddingValues ->
    LazyColumn(
      modifier = Modifier.fillMaxSize().padding(paddingValues),
    ) {
      items(uiState.itemsList) { item ->
        TodoItem(
          item = item,
          modifier = Modifier
            .fillMaxWidth()
            .clickable {
              viewModel.onIdItemChange(item.id)
              onNavigate(item.id)
            }
            .padding(16.dp),
          onDeleteItem = viewModel::onDeleteItem,
          onCheckItem = viewModel::onCheckItem,
        )
      }
      }
  }
}