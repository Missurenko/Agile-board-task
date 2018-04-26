package ua.com.mycompany.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.dto.TaskDto;

@Service
public class TaskConvector implements Converter<TaskDto, Task> {
    @Override
    public Task convert(TaskDto taskDto) {
        Task task = new Task();
        if (taskDto.getId() != null) {
            task.setId(Long.parseLong(taskDto.getId()));
        }
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setBoardId(Long.parseLong(taskDto.getBoardId()));
        return task;
    }
}
