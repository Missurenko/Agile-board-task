package ua.com.mycompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mycompany.dao.TaskRepository;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.service.TaskService;
import ua.com.mycompany.utils.NextSequenceService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    NextSequenceService nextSequenceService;

    @Override
    public List<Task> getAllByBoardId(Long id) {
        return taskRepository.findAllByBoardId(id);

    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }


    @Override
    public Task create(Task task) {
        task.setId(nextSequenceService.getNextSequence("taskIdSequences"));
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.delete(id);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findOne(id);
    }
}
