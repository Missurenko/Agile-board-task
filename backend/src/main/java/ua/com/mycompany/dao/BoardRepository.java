package ua.com.mycompany.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.mycompany.domain.Board;


public interface BoardRepository extends MongoRepository<Board, Long> {


    Board findById(Long id);

    Board findByName(String name);
}
