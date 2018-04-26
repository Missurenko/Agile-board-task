package ua.com.mycompany.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    String id;
    @NonNull
    String name;

    String description;
    @NonNull
    String boardId;
}
