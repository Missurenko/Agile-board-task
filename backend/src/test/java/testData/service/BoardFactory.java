package testData.service;

import ua.com.mycompany.domain.Board;

public class BoardFactory {
    public static Board toDoBoard(long id, String name) {
        Board board = new Board();
        board.setId(id);
        board.setName(name);
        return board;
    }
}
