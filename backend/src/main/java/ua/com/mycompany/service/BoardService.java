package ua.com.mycompany.service;

import ua.com.mycompany.domain.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAll();

    Board create(Board board);

    Board update(Board board);

    void deleteById(Long id);

    Board findById(Long id);
}
