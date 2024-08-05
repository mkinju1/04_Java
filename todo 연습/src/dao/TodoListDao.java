package dao;

import dto.Todo;
import java.io.IOException;
import java.util.List;

public interface TodoListDao {

    List<Todo> getAllTodos();
    Todo getTodoById(int id);
    boolean addTodo(Todo todo) throws IOException;
    void deleteTodo(int id) throws IOException;
    void saveTodos() throws IOException;
}