package report.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.assessing.Assessor;
import report.client.NoteClient;
import report.client.PatientClient;
import report.model.Note;
import report.model.Patient;
import report.model.PatientInformation;
import report.model.Report;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private PatientClient patientClient;
    @Autowired
    private NoteClient noteClient;
    private Assessor assessor = new Assessor();

    public ReportService(){}

    public ReportService(Assessor assessor, PatientClient patientClient, NoteClient noteClient){ // tests purpose
        this.assessor = assessor;
        this.patientClient = patientClient;
        this.noteClient = noteClient;
    }

    public Report getReport(Patient patient, List<Note> notes) {
        assessor.setPatientInformation(new PatientInformation(patient, notes));
        return assessor.generateReport();
    }

    public Patient getPatientById(long patientId) throws FeignException.NotFound {
        return patientClient.getPatientById(patientId);
    }

    public List<Note> getNoteByPatientId(long patientId) {
        return noteClient.getNoteByPatientId(patientId);
    }
}
