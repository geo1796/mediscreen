package report.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Note implements Comparable<Note>{

    private String id;

    private long patientId;

    private String content;

    private String creationDate;

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

    public String getContent() {
        return content;
    }

    public String getCreationDate() {
        return creationDate;
    }
}
