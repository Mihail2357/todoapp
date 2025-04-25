package com.example.myapplication.todolistitems

import androidx.room.Entity
import androidx.room.PrimaryKey

data class TodoListUiState(
  val itemsList: List<Item>,
)

@Entity(tableName = "todo_table") // Define table name
data class Item(
  val title: String,
  val description: String,
  val isChecked: Boolean = false,
  @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated primary key
)

