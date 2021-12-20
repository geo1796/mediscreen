package patients.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.jsoniter.output.JsonStream;
import patients.model.Patient;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PatientControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PatientController patientController;

    @Test
    public void testGetPatientWithControllerInstance() throws Exception {
        assertTrue(patientController.getPatientById(1).getStatusCode().is2xxSuccessful());
        assertEquals(patientController.getPatientById(1).getBody().getFirstName(), "Goku");
    }

    @Test
    public void testGetPatientById() throws Exception {
        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Goku")));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCreatePatient() throws Exception {
        mockMvc.perform(post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(new Patient("Prince", "Vegeta", "M",
                        "CapsuleCorp", "0123456789", "1975-01-01"))))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/patient/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Vegeta")));
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeletePatient() throws Exception {
        mockMvc.perform(get("/patient"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(1)));

        mockMvc.perform(delete("/patient/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testUpdatePatient() throws Exception {
        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Goku")));

        Patient patient = new Patient(
                "Prince", "Vegeta", "M",
                "CapsuleCorp", "0123456789", "1975-01-01");
        mockMvc.perform(put("/patient/1")
                .contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(patient)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Vegeta")));
    }

    @Test
    public void testGetNotExistingPatient() throws Exception {
        mockMvc.perform(get("/patient/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateNotExistingPatient() throws Exception {
        mockMvc.perform(put("/patient/99999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStream.serialize(new Patient(
                                "Son", "Gohan", "M", "Namek",
                                "9999999999", "2000-01-01"))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteNotExistingPatient() throws Exception {
        mockMvc.perform(delete("/patient/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNotValidPatient() throws Exception {
        mockMvc.perform(post("/patient/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStream.serialize(new Patient())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testUpdateNotValidPatient() throws Exception {
        mockMvc.perform(put("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStream.serialize(new Patient())))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

}
