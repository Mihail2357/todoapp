package com.example.myapplication.data

import com.example.myapplication.todolistitems.Item
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: Item)

    suspend fun deleteTodo(todo: Item)

    suspend fun updateTodo(todo: Item)

    suspend fun getTodoById(id: Int): Item?

    fun getTodos(): Flow<List<Item>>
}