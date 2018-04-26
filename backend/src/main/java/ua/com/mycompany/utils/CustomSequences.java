package ua.com.mycompany.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customSequences")
@Getter
@Setter
public class CustomSequences {
    @Id
    private String id;
    private int seq;

}
