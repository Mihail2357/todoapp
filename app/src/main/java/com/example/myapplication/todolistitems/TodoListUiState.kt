package com.example.myapplication.todolistitems

data class TodoListUiState(
  val itemsList: List<Item>,
)

data class Item(
  val title: String,
  val description: String,
  var isChecked: Boolean = false,
  val id: Int,
)

