package dao;

import dto.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoListDaoImpl implements TodoListDao {

    private static final String FILE_PATH = "todos.dat";
    private List<Todo> todoList;
    private AtomicInteger idCounter;

    public TodoListDaoImpl() throws IOException {
        todoList = loadTodos();
        idCounter = new AtomicInteger(todoList.size() > 0 ? todoList.get(todoList.size() - 1).getId() : 0);
    }

    private List<Todo> loadTodos() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Todo>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("파일 읽기 오류", e);
        }
    }

    @Override
    public List<Todo> getAllTodos() {
        return new ArrayList<>(todoList);
    }

    @Override
    public Todo getTodoById(int id) {
        return todoList.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addTodo(Todo todo) throws IOException {
        todo.setId(idCounter.incrementAndGet());
        todoList.add(todo);
        saveTodos();
        return true;
    }

    @Override
    public void deleteTodo(int id) throws IOException {
        todoList.removeIf(todo -> todo.getId() == id);
        saveTodos();
    }

    @Override
    public void saveTodos() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(todoList);
        }
    }
}