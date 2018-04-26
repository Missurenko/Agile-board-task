package ua.com.mycompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.mycompany.dao.BoardRepository;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.service.BoardService;
import ua.com.mycompany.service.TaskService;

@SpringBootApplication
public class Application implements ApplicationRunner {
    @Autowired
    private BoardService boardService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private BoardRepository boardRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boardRepository.deleteAll();
        boardService.create(Board.builder().name("ToDo").build());
        boardService.create(Board.builder().name("Done").build());
        Board progress = boardService.create(Board.builder().name("In Progress").build());
        taskService.create(Task.builder().boardId(progress.getId()).name("TestName").description("test description").build());
        //boardService.create(new Board(null,"end"));
    }
}
