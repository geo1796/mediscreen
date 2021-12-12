package notes.controller;

import lombok.AllArgsConstructor;
import notes.dto.NoteDto;
import notes.exception.ResourceNotFoundException;
import notes.model.Note;
import notes.service.NoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class NoteController {

    private static final Logger logger = LogManager.getLogger(NoteController.class);
    private NoteService noteService;

    @PostMapping("/patHistory/add")
    public ResponseEntity<Note> addNote(@RequestBody @Valid NoteDto noteDto) {
        return new ResponseEntity<>(noteService.save(noteDto), HttpStatus.CREATED);
    }

    @GetMapping("/patHistory")
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patHistory/{patientId}")
    public ResponseEntity<List<Note>> getNoteByPatientId(@PathVariable long patientId) {
        logger.info("method getNoteByPatientId called : patientId = " + patientId);
        return new ResponseEntity<>(noteService.findByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/patHistory/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) throws ResourceNotFoundException {
        Optional<Note> optionalNote = noteService.findById(id);
        if (optionalNote.isEmpty()){
            throw new ResourceNotFoundException("there is no note with id : " + id);
        }

        return new ResponseEntity<>(optionalNote.get(), HttpStatus.OK);
    }

    @PutMapping("/patHistory/{id}")
    public ResponseEntity<Note> UpdateNote(@PathVariable String id, @RequestBody @Valid NoteDto noteDetails) throws ResourceNotFoundException {
        Optional<Note> optionalNote = noteService.findById(id);
        if (optionalNote.isEmpty()){
            throw new ResourceNotFoundException("no note with id : " + id);
        }

        return new ResponseEntity<>(noteService.update(optionalNote.get(), noteDetails), HttpStatus.OK);
    }

}
