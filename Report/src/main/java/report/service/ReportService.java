package report.service;

import org.springframework.stereotype.Service;
import report.assessing.Assessor;
import report.client.NoteClient;
import report.client.PatientClient;
import report.model.PatientInformation;
import report.model.Report;
import lombok.AllArgsConstructor;
import notes.model.Note;
import patients.model.Patient;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportService {

    private PatientClient patientClient;
    private NoteClient noteClient;

    public Report getReport(Patient patient, List<Note> notes) {
        Assessor assessor = new Assessor(new PatientInformation(patient, notes));
        return assessor.generateReport();
    }

    public Optional<Patient> getPatientById(long patientId) {
        return Optional.ofNullable(patientClient.getPatientById(patientId));
    }

    public Optional<List<Note>> getNoteByPatientId(long patientId) {
        return Optional.ofNullable(noteClient.getNoteByPatientId(patientId));
    }
}
