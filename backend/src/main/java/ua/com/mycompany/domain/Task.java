package ua.com.mycompany.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Field(value = "id")
    @Id
    private Long id;
    @Field(value = "name")
    private String name;
    @Field(value = "description")
    private String description;
    @Field(value = "boardId")
    private Long boardId;
}
