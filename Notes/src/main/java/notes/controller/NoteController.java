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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class NoteController {

    private static final Logger logger = LogManager.getLogger(NoteController.class);
    private NoteService noteService;

    @PostMapping("/patHistory/add")
    public ResponseEntity<Note> addNote(@RequestBody @Valid NoteDto noteDto) {
        logger.info("calling method add Note : RequestBody = " + noteDto.toString());
        return new ResponseEntity<>(noteService.save(noteDto), HttpStatus.CREATED);
    }

    @GetMapping("/patHistory")
    public ResponseEntity<List<Note>> getAllNotes() {
        logger.info("calling method getAllNotes");
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patHistory/{patientId}")
    public ResponseEntity<List<Note>> getNoteByPatientId(@PathVariable long patientId) {
        logger.info("method getNoteByPatientId called : patientId = " + patientId);
        return new ResponseEntity<>(noteService.findByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/patHistory/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) throws ResourceNotFoundException {
        logger.info("calling method getNoteById with id = " + id);
        Optional<Note> optionalNote = noteService.findById(id);
        if (optionalNote.isEmpty()){
            throw new ResourceNotFoundException("there is no note with id : " + id);
        }

        return new ResponseEntity<>(optionalNote.get(), HttpStatus.OK);
    }

    @PutMapping("/patHistory/{id}")
    public ResponseEntity<Note> UpdateNote(@PathVariable String id, @RequestBody @Valid NoteDto noteDetails) throws ResourceNotFoundException {
        logger.info("calling method Update note : id = " + id
            + " ; RequestBody = " + noteDetails.toString());
        Optional<Note> optionalNote = noteService.findById(id);
        if (optionalNote.isEmpty()){
            throw new ResourceNotFoundException("no note with id : " + id);
        }

        return new ResponseEntity<>(noteService.update(optionalNote.get(), noteDetails), HttpStatus.OK);
    }

    @DeleteMapping("patHistory/note/{id}")
    public Map<String, Boolean> deleteNote(@PathVariable String id) throws ResourceNotFoundException {
        logger.info("method deleteNote called for id = " + id);
        Optional<Note> optionalNote = noteService.findById(id);

        if (optionalNote.isEmpty()) {
            throw new ResourceNotFoundException("Patient not found for id = " + id);
        }

        noteService.delete(optionalNote.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
