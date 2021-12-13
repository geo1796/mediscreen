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

    @Field(value = "content")
    private String content;

    @Field(value = "creationDate")
    private LocalDate creationDate;

    @Field(value = "lastUpdate")
    private LocalDate lastUpdate;

    public Note(long patientId, String content) {
        this.patientId = patientId;
        this.content = content;
        this.creationDate = LocalDate.now();
        this.lastUpdate = this.creationDate;
    }

    public Note(long patientId, String content, String creationDate) {
        this.patientId = patientId;
        this.content = content;
        this.creationDate = LocalDate.parse(creationDate);
        this.lastUpdate = LocalDate.now();
    }
}
