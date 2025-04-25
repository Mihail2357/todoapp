package com.example.myapplication.todolistitems

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.TodoDatabase
import com.example.myapplication.data.TodoRepositoryImpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoListViewModel(application: Application) : AndroidViewModel(application) {

  // TodoList screen

  private val repository = TodoRepositoryImpl(TodoDatabase.getDatabase(application).dao)

  val uiState: StateFlow<TodoListUiState> = repository.getTodos().map { itemList ->
    TodoListUiState(itemList)
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TodoListUiState(emptyList()))

  fun onAddItem() {
    Log.d(TAG, "Add item")
    val newItem = Item(title = "Item", description = "Simple Description")
    viewModelScope.launch {
      repository.insertTodo(newItem)
    }
  }

  fun onDeleteItem(item: Item) {
    Log.d(TAG, "Delete item with id = ${item.id}")
    viewModelScope.launch {
      repository.deleteTodo(item)
    }
  }

  fun onCheckItem(item: Item, isChecked: Boolean) {
    val newItem = item.copy(isChecked = isChecked)

    viewModelScope.launch {
      repository.updateTodo(newItem)
    }
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

    val newTitle = title
    val newDescription = description
    viewModelScope.launch {
      if(currentItemID != null) {
        val currentItem = repository.getTodoById(currentItemID!!)
        val newItem = currentItem?.copy(title = newTitle, description = newDescription)
        if (newItem != null) {
          repository.updateTodo(newItem)
        }
      }
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