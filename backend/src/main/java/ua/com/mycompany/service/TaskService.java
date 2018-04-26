package ua.com.mycompany.service;

import ua.com.mycompany.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllByBoardId(Long id);

    List<Task> getAll();

    Task create(Task task);

    Task update(Task task);

    void deleteById(Long id);

    Task findById(Long id);
}
