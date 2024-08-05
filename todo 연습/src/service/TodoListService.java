package service;

import dto.Todo;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoListService {

    List<Todo> getAllTodos();
    Todo getTodoById(int id);
    boolean addTodo(String title, String detail, LocalDateTime regDate) throws IOException;
    boolean markTodoComplete(int id) throws IOException;
    boolean updateTodo(int id, String title, String detail) throws IOException;
    boolean deleteTodo(int id) throws IOException;
}