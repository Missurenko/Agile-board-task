package ua.com.mycompany.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.dto.BoardDto;


@Service
public class BoardConverter implements Converter<BoardDto, Board> {

    @Override
    public Board convert(BoardDto boardDto) {
        Board board = new Board();
        if (boardDto.getId() != null) {
            board.setId(Long.valueOf(boardDto.getId()));
        }
        board.setName(boardDto.getName());
        return board;
    }
}
