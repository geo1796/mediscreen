package notes.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "note")
public class Note {

    @Id
    private String id;

    @Field(value = "patientId")
    private long patientId;

    @Field(value = "patientLastName")
    private String patientLastName;

    @Field(value = "patientFirstName")
    private String patientFirstName;

    @Field(value = "content")
    private String content;

    @Field(value = "date")
    private LocalDate date;

    public Note(long patientId, String patientLastName, String patientFirstName, String content, LocalDate date) {
        this.patientId = patientId;
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.content = content;
        this.date = date;
    }
}
