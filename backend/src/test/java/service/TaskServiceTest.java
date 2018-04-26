package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import testData.service.TaskFactory;
import ua.com.mycompany.dao.TaskRepository;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.service.impl.TaskServiceImpl;
import ua.com.mycompany.utils.NextSequenceService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private final static Task DO_AGILE_BOARD = TaskFactory.currentTask();

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskServiceImpl taskServiceimpl;
    @Mock
    private NextSequenceService nextSequenceService;
    @Test
    public void UpdateBoardSuccessful() {

        taskServiceimpl.update(DO_AGILE_BOARD);
        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository, new Times(1)).save(argumentCaptor.capture());
        Task task = argumentCaptor.getValue();
        assertEquals(DO_AGILE_BOARD.getName(), task.getName());
    }

    @Test
    public void saveSuccessful() {
        when(nextSequenceService.getNextSequence(anyString())).thenReturn(0L);
        taskServiceimpl.create(DO_AGILE_BOARD);
        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository, new Times(1)).save(argumentCaptor.capture());
        Task task = argumentCaptor.getValue();
        assertEquals(DO_AGILE_BOARD.getName(), task.getName());

    }

    @Test
    public void deleteSuccessful() {

        Long id = 55L;
        taskServiceimpl.deleteById(id);
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(taskRepository, new Times(1)).delete(argumentCaptor.capture());
        Long board = argumentCaptor.getValue();
        assertEquals(id, board);
    }
}
