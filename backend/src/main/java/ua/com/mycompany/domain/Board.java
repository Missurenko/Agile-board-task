package ua.com.mycompany.domain;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Document(collection = "boards")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board implements Serializable {


    @Field(value = "id")
    @Id
    private Long id;
    @Field(value = "name")
    private String name;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
