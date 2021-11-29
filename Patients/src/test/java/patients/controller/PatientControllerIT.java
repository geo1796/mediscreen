package patients.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import patients.dto.PatientDto;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.jsoniter.output.JsonStream;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PatientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPatient() throws Exception {
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Goku")));
    }

    @Test
    public void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCreatePatient() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(new PatientDto("Prince", "Vegeta", "M",
                        "CapsuleCorp", "0123456789", "1975-01-01"))))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/patients/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Vegeta")));
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testDeletePatient() throws Exception {
        mockMvc.perform(get("/patients"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(1)));

        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testUpdatePatient() throws Exception {
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Goku")));

        PatientDto patientDto = new PatientDto(
                "Prince", "Vegeta", "M",
                "CapsuleCorp", "0123456789", "1975-01-01");
        mockMvc.perform(put("/patients/1")
                .contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(patientDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Vegeta")));
    }

}
