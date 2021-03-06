package by.avp.springbootmongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "todos")
public class TodoDTO {

    @Id
    private String id;

    @NotNull(message = "Todo cannot be null")
    private String todo;

    @NotNull(message = "Descriptions cannot be null")
    private String descriptions;

    @NotNull(message = "Completed cannot be null")
    private Boolean completed;

    private Date createdAt;

    private Date updatedAt;
}
