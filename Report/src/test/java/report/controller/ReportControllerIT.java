package report.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetReportOk() throws Exception {
        mockMvc.perform(get("/assess/1"))
                .andExpect(status().isOk());
        //TODO handle the FileNotFoundException
    }

    @Test
    public void testGetReportForNotExistingPatientId() throws Exception {
        mockMvc.perform(get("/assess/0"))
                .andExpect(status().isNotFound());
    }

}
