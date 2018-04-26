package ua.com.mycompany.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.dto.BoardDto;

@Service
public class BoardDtoConverter implements Converter<Board, BoardDto> {

    @Override
    public BoardDto convert(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setName(board.getName());
        boardDto.setId(String.valueOf(board.getId()));
        return boardDto;
    }
}
