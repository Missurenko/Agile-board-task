package ua.com.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.dto.BoardDto;
import ua.com.mycompany.exeption.ObjectByIdNotFoundExeption;
import ua.com.mycompany.exeption.ServerErrorExeption;
import ua.com.mycompany.service.BoardService;
import ua.com.mycompany.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/board", produces = {"application/json"})
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ConversionService conversionService;

    @GetMapping
    @ResponseBody
    public List<BoardDto> getAll() {
        return boardService.getAll()
                .stream()
                .map(board -> conversionService.convert(board, BoardDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BoardDto create(@RequestBody BoardDto boardDto) {
        if (boardDto.getId() != null) {
            throw new ServerErrorExeption(boardDto.getId());
        }
        Board board = conversionService.convert(boardDto, Board.class);
        Board newBoard = boardService.create(board);
        return conversionService.convert(newBoard, BoardDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public BoardDto update(@PathVariable(value = "id") Long id, @RequestBody BoardDto boardDto) {
        Board board = conversionService.convert(boardDto, Board.class);
        Board boardFromDao = boardService.findById(id);
        if (boardFromDao == null) {
            throw new ObjectByIdNotFoundExeption(id, Board.class.getName());
        }
        Board updateBoard = boardService.update(board);
        return conversionService.convert(updateBoard, BoardDto.class);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        Board byId = boardService.findById(id);
        if (byId == null) {
            throw new ObjectByIdNotFoundExeption(id, Board.class.getName());
        }
        boardService.deleteById(id);
    }

}
