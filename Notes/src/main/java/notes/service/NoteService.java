package notes.service;

import lombok.AllArgsConstructor;
import notes.dto.NoteDto;
import notes.mapper.NoteMapper;
import notes.model.Note;
import notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;
    private NoteMapper noteMapper;

    public Note save(Note note) { return noteRepository.save(note); }

    public Note save(NoteDto noteDto) { return save(noteMapper.toEntity(noteDto)); }
}
