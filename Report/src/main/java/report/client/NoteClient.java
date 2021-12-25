package report.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import report.model.Note;

import java.util.List;

@FeignClient(name = "note", url = "${note.base-path}")
public interface NoteClient {

    @RequestMapping(method = RequestMethod.GET, value = "/patHistory/{patientId}")
    List<Note> getNoteByPatientId(@PathVariable("patientId") long patientId);

}
