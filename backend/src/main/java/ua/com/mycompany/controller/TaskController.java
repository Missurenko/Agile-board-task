package ua.com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.dto.TaskDto;
import ua.com.mycompany.exeption.ObjectByIdNotFoundExeption;
import ua.com.mycompany.exeption.ServerErrorExeption;
import ua.com.mycompany.service.BoardService;
import ua.com.mycompany.service.TaskService;
import ua.com.mycompany.utils.NextSequenceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/task", produces = {"application/json"})
public class TaskController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private NextSequenceService nextSequenceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<TaskDto> getAll() {
        List<Task> taskServiceAll = taskService.getAll();
        List<TaskDto> taskDtos = taskServiceAll
                .stream()
                .map(task -> conversionService.convert(task, TaskDto.class))
                .collect(Collectors.toList());
        return taskDtos;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDto create(@RequestBody TaskDto taskDto) {
        if (taskDto.getId() != null) {
            throw new ServerErrorExeption(taskDto.getId());
        }
        Task task = conversionService.convert(taskDto, Task.class);
        Task newTask = taskService.create(task);
        return conversionService.convert(newTask, TaskDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public TaskDto update(@PathVariable(value = "id") Long id, @RequestBody TaskDto taskDto) {
        Task task = conversionService.convert(taskDto, Task.class);
        Task taskFromDao = taskService.findById(id);
        if (taskFromDao == null) {
            throw new ObjectByIdNotFoundExeption(id, Task.class.getName());
        }
        Task updateTask = taskService.update(task);
        return conversionService.convert(updateTask, TaskDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        Task task = taskService.findById(id);
        if (task == null) {
            throw new ObjectByIdNotFoundExeption(id, Task.class.getName());
        }
        taskService.deleteById(id);
    }

}
