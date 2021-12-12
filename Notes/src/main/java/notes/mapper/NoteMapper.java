package notes.mapper;

import notes.dto.NoteDto;
import notes.model.Note;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoteMapper {

    public Note toEntity(NoteDto noteDto) {
        return new Note(
                noteDto.getPatientId(), noteDto.getPatientLastName(),
                noteDto.getPatientFirstName(), noteDto.getContent());
    }

}
