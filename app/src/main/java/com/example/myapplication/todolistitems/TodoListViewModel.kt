package com.example.myapplication.todolistitems

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoListViewModel : ViewModel() {

  // TodoList screen
  private var counter = 1
  private val currentList = mutableListOf<Item>()
  private val _uiState = MutableStateFlow(TodoListUiState(emptyList()))

  val uiState = _uiState.asStateFlow()

  fun onAddItem() {
    Log.d(TAG, "Add item with id = $counter")
    val newItem = Item(title = "Item $counter", description = "Simple Description $counter", id = counter++)
    currentList.add(newItem)
    _uiState.value = TodoListUiState(currentList.toList())
  }

  fun onDeleteItem(id: Int) {
    Log.d(TAG, "Delete item with id = $id")
    currentList.removeIf { it.id == id }
    _uiState.value = TodoListUiState(currentList.toList())
  }

  fun onCheckItem(id: Int, isChecked: Boolean) {
    val updatedList = currentList.map { item ->
      if (item.id == id) {
        Log.d(TAG, "Change isChecked for item id = $id from ${item.isChecked} to $isChecked")
        item.copy(isChecked = isChecked)
      } else {
        item
      }
    }

    currentList.clear() // Clear the old list
    currentList.addAll(updatedList) // Add the new list

    _uiState.value = TodoListUiState(currentList.toList())
  }

   // EditTodoScreen

  var title by mutableStateOf("")

  var description by mutableStateOf("")

  private var currentItemID by mutableStateOf<Int?>(null)

  fun onTitleChange(newTitle: String) {
    title = newTitle
  }

  fun onDescriptionChange(newDescription: String) {
    description = newDescription
  }

  fun onIdItemChange(newItemId: Int) {
    currentItemID = newItemId
  }

  fun saveChanges() {
    Log.d(TAG, "Change item $currentItemID")
    if(currentItemID != null) {
      val updatedList = currentList.map { item ->
        if (item.id == currentItemID) {
          Log.d(TAG, "Change item with id = $currentItemID")
          item.copy(title = title, description = description)
        } else {
          item
        }
      }

      currentList.clear() // Clear the old list
      currentList.addAll(updatedList) // Add the new list

      _uiState.value = TodoListUiState(currentList.toList())
    }
  }

  fun resetValues() {
    title = ""
    description = ""
  }

  private companion object {
    const val TAG = "TodoListViewModel"
  }
}