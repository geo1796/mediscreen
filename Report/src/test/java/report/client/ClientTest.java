package report.client;

import notes.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import patients.model.Patient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClientTest {

    @Autowired
    PatientClient patientClient;
    @Autowired
    NoteClient noteClient;

    @Test
    public void testGetPatientById() throws Exception {
        assertNotNull(patientClient);
        Patient patient = patientClient.getPatientById(1L);
        System.out.println(patient);
    }

    @Test
    public void testGetNoteByPatientId() throws Exception {
        assertNotNull(noteClient);
        List<Note> notes = noteClient.getNoteByPatientId(1L);
        System.out.println(notes);
    }

}
