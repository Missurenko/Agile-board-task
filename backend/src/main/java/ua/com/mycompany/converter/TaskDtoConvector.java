package ua.com.mycompany.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.dto.TaskDto;

@Service
public class TaskDtoConvector implements Converter<Task, TaskDto> {
    @Override
    public TaskDto convert(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId().toString());
        taskDto.setDescription(task.getDescription());
        taskDto.setName(task.getName());
        taskDto.setBoardId(task.getBoardId().toString());
        return taskDto;
    }
}
