package report.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import report.model.Report;
import report.service.ReportService;
import lombok.AllArgsConstructor;
import notes.model.Note;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import patients.model.Patient;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ReportController {

    private static final Logger logger = LogManager.getLogger(ReportController.class);
    private ReportService reportService;

    @GetMapping("/assess/{patientId}")
    public ResponseEntity<Report> getReport(@PathVariable("patientId") long patientId) {
        logger.info("calling method getReport : patientId = " + patientId);
        Optional<Patient> optionalPatient = reportService.getPatientById(patientId);

        if (optionalPatient.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<List<Note>> optionalNotes = reportService.getNoteByPatientId(patientId);

        if (optionalNotes.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(reportService.getReport(optionalPatient.get(), optionalNotes.get()));
    }

}
