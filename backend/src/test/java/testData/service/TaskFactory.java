package testData.service;

import ua.com.mycompany.domain.Task;

public class TaskFactory {
    public static Task currentTask() {
        Task task = new Task();
        task.setDescription("Test tz");
        task.setName("Agile board");
        return task;
    }
}
