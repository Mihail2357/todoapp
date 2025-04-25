package com.example.myapplication.data

import com.example.myapplication.todolistitems.Item
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository {

    override suspend fun insertTodo(todo: Item) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Item) {
        dao.deleteTodo(todo)
    }

    override suspend fun updateTodo(todo: Item) {
        dao.updateTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Item? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Item>> {
        return dao.getTodos()
    }
}