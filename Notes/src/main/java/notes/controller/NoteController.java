package notes.controller;

import lombok.AllArgsConstructor;
import notes.dto.NoteDto;
import notes.model.Note;
import notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @PostMapping("/patHistory/add")
    public ResponseEntity<Note> addNote(@RequestBody @Valid NoteDto noteDto) {
        return new ResponseEntity<>(noteService.save(noteDto), HttpStatus.CREATED);
    }

}
