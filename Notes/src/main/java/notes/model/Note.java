package notes.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "note")
public class Note implements Comparable<Note>{

    @Id
    private String id;

    @Field(value = "patientId")
    @NotNull(message = "patientId is mandatory")
    private long patientId;

    @Field(value = "content")
    @NotBlank(message = "content is mandatory")
    private String content;

    @Field(value = "creationDate")
    private String creationDate;

    @Field(value = "lastUpdate")
    private String lastUpdate;

    public Note(long patientId, String content) {
        this.patientId = patientId;
        this.content = content;
    }

    @Override
    public String toString(){
        return "Note object : [id = " + id
                + " ; content = " + content
                + " ; creationDate = " + creationDate
                + " ; lastUpdate = " + lastUpdate + "]";
    }

    @Override
    public int compareTo(Note otherNote) {
        LocalDateTime thisDateTime = LocalDateTime.parse(
                this.creationDate,
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT));
        LocalDateTime otherDateTime = LocalDateTime.parse(
                otherNote.getCreationDate(),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT));
        if(thisDateTime.isBefore(otherDateTime)){
            return 1;
        }
        else if(otherDateTime.isBefore(thisDateTime)){
            return -1;
        }
        return 0;
    }
}
