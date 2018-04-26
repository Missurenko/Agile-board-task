package ua.com.mycompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mycompany.dao.BoardRepository;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.domain.Task;
import ua.com.mycompany.exeption.ObjectByNameExistExeption;
import ua.com.mycompany.service.BoardService;
import ua.com.mycompany.service.TaskService;
import ua.com.mycompany.utils.NextSequenceService;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private NextSequenceService nextSequenceService;

    @Override
    public List<Board> getAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board create(Board board) {
        board.setId(nextSequenceService.getNextSequence("boardIdSequences"));
        Board byName = boardRepository.findByName(board.getName());
        if (byName != null) {
            throw new ObjectByNameExistExeption(byName.getName(), byName.getName());
        }
        return boardRepository.save(board);
    }

    @Override
    public Board update(Board board) {
        Board byId = boardRepository.findById(board.getId());
        Board byName = boardRepository.findByName(board.getName());
        if (byName != null && !byId.getId().equals(byName.getId())) {
            throw new ObjectByNameExistExeption(byName.getName(), byName.getName());
        }
        return boardRepository.save(board);
    }


    @Override
    public void deleteById(Long id) {
        boardRepository.delete(id);
        for (Task task : taskService.getAllByBoardId(id)) {
            taskService.deleteById(task.getId());
        }
    }

    @Override
    public Board findById(Long id) {
        return boardRepository.findOne(id);
    }
}
