package service;

import dao.TodoListDao;
import dao.TodoListDaoImpl;
import dto.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TodoListServiceImpl implements TodoListService {

    private TodoListDao dao;

    public TodoListServiceImpl() throws IOException {
        dao = new TodoListDaoImpl();
    }

    @Override
    public List<Todo> getAllTodos() {
        return dao.getAllTodos();
    }

    @Override
    public Todo getTodoById(int id) {
        return dao.getTodoById(id);
    }

    @Override
    public boolean addTodo(String title, String detail, LocalDateTime regDate) throws IOException {
        return dao.addTodo(new Todo(0, title, detail, false, regDate));
    }

    @Override
    public boolean markTodoComplete(int id) throws IOException {
        Todo todo = dao.getTodoById(id);
        if (todo == null) {
            return false;
        }
        todo.setComplete(true);
        dao.saveTodos();
        return true;
    }

    @Override
    public boolean updateTodo(int id, String title, String detail) throws IOException {
        Todo todo = dao.getTodoById(id);
        if (todo == null) {
            return false;
        }
        todo.setTitle(title);
        todo.setDetail(detail);
        dao.saveTodos();
        return true;
    }

    @Override
    public boolean deleteTodo(int id) throws IOException {
        Todo todo = dao.getTodoById(id);
        if (todo == null) {
            return false;
        }
        dao.deleteTodo(id);
        dao.saveTodos();
        return true;
    }
}