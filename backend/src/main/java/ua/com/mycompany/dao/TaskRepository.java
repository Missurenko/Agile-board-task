package ua.com.mycompany.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.mycompany.domain.Task;

import java.util.List;


public interface TaskRepository extends MongoRepository<Task, Long> {

    List<Task> findAllByBoardId(Long id);
}
