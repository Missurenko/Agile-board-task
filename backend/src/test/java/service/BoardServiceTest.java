package service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import testData.service.BoardFactory;
import ua.com.mycompany.dao.BoardRepository;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.exeption.ObjectByNameExistExeption;
import ua.com.mycompany.service.TaskService;
import ua.com.mycompany.service.impl.BoardServiceImpl;
import ua.com.mycompany.utils.NextSequenceService;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceTest {


    private final static Board TO_DO1 = BoardFactory.toDoBoard(0, "toDo");
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private TaskService taskService;
    @Mock
    private NextSequenceService nextSequenceService;

    @InjectMocks
    private BoardServiceImpl boardServiceImpl;

    @Before
    public void init() {

    }

    @Test(expected = ObjectByNameExistExeption.class)
    public void duplicateExceptionCreate() {
        when(boardRepository.findByName("toDo")).thenReturn(TO_DO1);
        boardServiceImpl.create(TO_DO1);
    }

    @Test(expected = ObjectByNameExistExeption.class)
    public void duplicateExceptionUpdate() {
        when(boardRepository.findById(any())).thenReturn(TO_DO1);
        when(boardRepository.findByName("toDo")).thenReturn(BoardFactory.toDoBoard(1, "toDo"));
        boardServiceImpl.update(TO_DO1);
    }

    @Test
    public void deleteSuccessful() {
        when(taskService.getAllByBoardId(55L)).thenReturn(new ArrayList<Task>());
        Long id = 55L;
        boardServiceImpl.deleteById(id);
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(boardRepository, new Times(1)).delete(argumentCaptor.capture());
        Long board = argumentCaptor.getValue();
        assertEquals(id, board);
    }


    @Test
    public void UpdateBoardSuccessful() {
        when(boardRepository.findById(any())).thenReturn(TO_DO1);
        when(boardRepository.findByName(anyString())).thenReturn(TO_DO1);
        boardServiceImpl.update(TO_DO1);
        ArgumentCaptor<Board> argumentCaptor = ArgumentCaptor.forClass(Board.class);
        verify(boardRepository, new Times(1)).save(argumentCaptor.capture());
        Board board = argumentCaptor.getValue();
        assertEquals(TO_DO1.getName(), board.getName());
    }

    @Test
    public void saveSuccessful() {
        when(boardRepository.findByName(anyString())).thenReturn(null);
        when(nextSequenceService.getNextSequence(anyString())).thenReturn(0L);

        boardServiceImpl.create(TO_DO1);
        ArgumentCaptor<Board> argumentCaptor = ArgumentCaptor.forClass(Board.class);
        verify(boardRepository, new Times(1)).save(argumentCaptor.capture());
        Board board = argumentCaptor.getValue();
        assertEquals(TO_DO1.getName(), board.getName());

    }
}
