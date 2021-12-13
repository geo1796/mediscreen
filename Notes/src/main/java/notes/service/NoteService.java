package notes.service;

import lombok.AllArgsConstructor;

import notes.model.Note;
import notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;

    public Note save(Note note) {
        if(note.getCreationDate() == null){
            note.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)));
        }
        return noteRepository.save(note); }

    public List<Note> findAll() {
        List<Note> allNotes = noteRepository.findAll();
        Collections.sort(allNotes);
        return allNotes;
    }

    public List<Note> findByPatientId(long patientId) {
        List<Note> notesByPatientId = noteRepository.findByPatientId(patientId);
        Collections.sort(notesByPatientId);
        return notesByPatientId;
    }

    public Optional<Note> findById(String id) { return noteRepository.findById(id); }

    public Note update(Note noteToUpdate, Note noteDetails) {
        noteToUpdate.setContent(noteDetails.getContent());
        noteToUpdate.setLastUpdate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT)));
        return save(noteToUpdate);
    }

    public void delete(Note note) { noteRepository.delete(note); }
}
