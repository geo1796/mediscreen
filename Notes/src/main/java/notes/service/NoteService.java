package notes.service;

import lombok.AllArgsConstructor;
import notes.dto.NoteDto;
import notes.mapper.NoteMapper;
import notes.model.Note;
import notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;
    private NoteMapper noteMapper;

    public Note save(Note note) { return noteRepository.save(note); }

    public Note save(NoteDto noteDto) { return save(noteMapper.toEntity(noteDto)); }

    public List<Note> findAll() { return noteRepository.findAll(); }

    public List<Note> findByPatientId(long patientId) { return noteRepository.findByPatientId(patientId); }

    public Optional<Note> findById(String id) { return noteRepository.findById(id); }

    public Note update(Note noteToUpdate, NoteDto noteDetails) {
        noteToUpdate.setContent(noteDetails.getContent());
        return save(noteToUpdate);
    }

    public void delete(Note note) { noteRepository.delete(note); }
}
