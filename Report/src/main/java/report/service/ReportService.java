package report.service;

import feign.FeignException;
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

@Service
@AllArgsConstructor
public class ReportService {

    private PatientClient patientClient;
    private NoteClient noteClient;

    public Report getReport(Patient patient, List<Note> notes) {
        Assessor assessor = new Assessor(new PatientInformation(patient, notes));
        return assessor.generateReport();
    }

    public Patient getPatientById(long patientId) throws FeignException.NotFound {
        return patientClient.getPatientById(patientId);
    }

    public List<Note> getNoteByPatientId(long patientId) {
        return noteClient.getNoteByPatientId(patientId);
    }
}
