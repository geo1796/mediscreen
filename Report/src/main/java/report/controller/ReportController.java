package report.controller;

import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import report.exception.ResourceNotFoundException;
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

@RestController
@AllArgsConstructor
public class ReportController {

    private static final Logger logger = LogManager.getLogger(ReportController.class);
    private ReportService reportService;

    @GetMapping("/assess/{patientId}")
    public ResponseEntity<Report> getReport(@PathVariable("patientId") long patientId) throws ResourceNotFoundException {
        logger.info("calling method getReport : patientId = " + patientId);
        try {
            Patient patient = reportService.getPatientById(patientId);
            List<Note> notes = reportService.getNoteByPatientId(patientId);

            return ResponseEntity.ok(reportService.getReport(patient, notes));
        }
        catch (FeignException.NotFound e){ // if there is no patient for this ID
            logger.error(e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

}
