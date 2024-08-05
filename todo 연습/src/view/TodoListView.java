package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dto.Todo;
import service.TodoListService;
import service.TodoListServiceImpl;

public class TodoListView {

    private TodoListService service;
    private BufferedReader br;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TodoListView() {
        try {
            service = new TodoListServiceImpl();
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.out.println("*** 프로그램 실행 중 오류 발생 ***");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void mainMenu() {
        int input = 0;
        do {
            try {
                input = selectMenu();

                switch (input) {
                    case 1: viewAllTodos(); break;
                    case 2: viewTodoDetail(); break;
                    case 3: addTodo(); break;
                    case 4: markTodoComplete(); break;
                    case 5: updateTodo(); break;
                    case 6: deleteTodo(); break;
                    case 0: System.out.println("*** 프로그램 종료 ***"); break;
                    default: System.out.println("### 메뉴에 작성된 번호만 입력 해주세요 ###");
                }

                System.out.println("=====================================");
            } catch (NumberFormatException e) {
                System.out.println("\n### 숫자만 입력 해주세요 ###\n");
                input = -1;
            } catch (IOException e) {
                System.out.println("\n### 입출력 관련 예외 발생 ###\n");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (input != 0);
    }

    private int selectMenu() throws NumberFormatException, IOException {
        System.out.println("\n===== Todo 관리 프로그램 =====\n");
        System.out.println("1. Todo 전체 목록 보기");
        System.out.println("2. Todo 세부 보기");
        System.out.println("3. Todo 추가");
        System.out.println("4. Todo 완료");
        System.out.println("5. Todo 수정");
        System.out.println("6. Todo 삭제");
        System.out.println("0. 종료");
        System.out.print("메뉴 선택 >>> ");
        int input = Integer.parseInt(br.readLine());
        System.out.println();
        return input;
    }

    private void viewAllTodos() {
        System.out.println("\n----- Todo 전체 목록 -----\n");
        List<Todo> todoList = service.getAllTodos();
        if (todoList.isEmpty()) {
            System.out.println("\n### Todo가 존재하지 않습니다 ###\n");
            return;
        }
        System.out.println("-------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-25s %s\n", "[ID]", "[제목]", "[상세 내용]", "[등록 날짜]", "[상태]");
        System.out.println("-------------------------------------------");
        for (Todo todo : todoList) {
            System.out.printf("%-5d %-20s %-20s %-25s %s\n", todo.getId(), todo.getTitle(), todo.getDetail(), todo.getRegDate().format(formatter), todo.isComplete() ? "완료" : "미완료");
        }
    }

    private void viewTodoDetail() throws IOException {
        System.out.println("\n----- Todo 세부 보기 -----\n");
        System.out.print("조회할 Todo ID 입력 : ");
        int id = Integer.parseInt(br.readLine());
        Todo todo = service.getTodoById(id);
        if (todo == null) {
            System.out.println("\n### 해당 Todo가 존재하지 않습니다 ###\n");
            return;
        }
        System.out.println(todo);
    }

    private void addTodo() throws IOException {
        System.out.println("\n----- Todo 추가 -----\n");
        System.out.print("Todo 제목 입력 : ");
        String title = br.readLine();
        System.out.print("Todo 상세 내용 입력 : ");
        String detail = br.readLine();
        LocalDateTime regDate = LocalDateTime.now();
        boolean result = service.addTodo(title, detail, regDate);
        if (result) {
            System.out.println("\n*** Todo가 추가되었습니다 ***\n");
        } else {
            System.out.println("\n### Todo 추가 실패 ###\n");
        }
    }

    private void markTodoComplete() throws IOException {
        System.out.println("\n----- Todo 완료 처리 -----\n");
        System.out.print("완료할 Todo ID 입력 : ");
        int id = Integer.parseInt(br.readLine());
        boolean result = service.markTodoComplete(id);
        if (result) {
            System.out.println("\n*** Todo가 완료 처리되었습니다 ***\n");
        } else {
            System.out.println("\n### Todo 완료 처리 실패 ###\n");
        }
    }

    private void updateTodo() throws IOException {
        System.out.println("\n----- Todo 수정 -----\n");
        System.out.print("수정할 Todo ID 입력 : ");
        int id = Integer.parseInt(br.readLine());
        Todo todo = service.getTodoById(id);
        if (todo == null) {
            System.out.println("\n### 해당 Todo가 존재하지 않습니다 ###\n");
            return;
        }
        System.out.print("새 제목 입력 : ");
        String newTitle = br.readLine();
        System.out.print("새 상세 내용 입력 : ");
        String newDetail = br.readLine();
        boolean result = service.updateTodo(id, newTitle, newDetail);
        if (result) {
            System.out.println("\n*** Todo가 수정되었습니다 ***\n");
        } else {
            System.out.println("\n### Todo 수정 실패 ###\n");
        }
    }

    private void deleteTodo() throws IOException {
        System.out.println("\n----- Todo 삭제 -----\n");
        System.out.print("삭제할 Todo ID 입력 : ");
        int id = Integer.parseInt(br.readLine());
        boolean result = service.deleteTodo(id);
        if (result) {
            System.out.println("\n*** Todo가 삭제되었습니다 ***\n");
        } else {
            System.out.println("\n### Todo 삭제 실패 ###\n");
        }
    }
}