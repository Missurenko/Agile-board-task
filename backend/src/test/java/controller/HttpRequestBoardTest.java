package controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.mycompany.Application;
import ua.com.mycompany.domain.Board;
import ua.com.mycompany.dto.BoardDto;
import ua.com.mycompany.exeption.ApiError;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class HttpRequestBoardTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void serverErrorExeptionCreateBoard() {
        BoardDto boardDto = new BoardDto();
        boardDto.setId("1");
        HttpEntity<BoardDto> request = new HttpEntity<>(boardDto);
        ResponseEntity<ApiError> response = restTemplate
                .exchange("http://localhost:" + port + "/api/board/", HttpMethod.POST, request, ApiError.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
        System.out.println(response.getBody().getMessage());
        String message = "Server logic error, not must send " + "this id " + boardDto.getId();
        assertEquals(response.getBody().getMessage(), message);
    }

    @Test
    public void notFoundExeptionUpdateBoard() {
        BoardDto boardDto = new BoardDto();
        String id = "1";
        boardDto.setId(id);
        HttpEntity<BoardDto> request = new HttpEntity<>(boardDto);
        ResponseEntity<ApiError> response = restTemplate
                .exchange("http://localhost:" + port + "/api/board/" + id + "/", HttpMethod.PUT, request, ApiError.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        System.out.println(response.getBody().getMessage());
        String message = "NOT found " + Board.class.getName() + " with this id " + boardDto.getId();
        assertEquals(response.getBody().getMessage(), message);
    }

    @Test
    public void notFoundExeptionDeleteBoard() {
        BoardDto boardDto = new BoardDto();
        String id = "0";
        boardDto.setId(id);
        HttpEntity<BoardDto> request = new HttpEntity<>(boardDto);
        ResponseEntity<ApiError> response = restTemplate
                .exchange("http://localhost:" + port + "/api/board/" + id + "/", HttpMethod.DELETE, request, ApiError.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
        System.out.println(response.getBody().getMessage());
        String message = "NOT found " + Board.class.getName() + " with this id " + boardDto.getId();
        assertEquals(response.getBody().getMessage(), message);
    }

}
