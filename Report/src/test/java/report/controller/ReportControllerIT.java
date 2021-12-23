package report.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import report.assessing.Assessor;
import report.client.NoteClient;
import report.client.PatientClient;
import report.exception.ResourceNotFoundException;
import report.service.ReportService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerIT {

    @Autowired
    PatientClient patientClient;
    @Autowired
    NoteClient noteClient;

    @Test
    public void testGetReportOk() throws Exception {
        Assessor assessor = new Assessor("src/test/resources/triggers");
        ReportService reportService = new ReportService(assessor, patientClient, noteClient);
        ReportController reportController = new ReportController(reportService);

        assertEquals(reportController.getReport(1).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetReportForNotExistingPatientId() {
        ReportService reportService = new ReportService(new Assessor(), patientClient, noteClient);
        ReportController reportController = new ReportController(reportService);

        assertThrows(ResourceNotFoundException.class, () -> reportController.getReport(0));
    }

}
