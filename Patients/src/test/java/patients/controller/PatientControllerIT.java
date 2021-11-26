package patients.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import patients.dto.PatientDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.jsoniter.output.JsonStream;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PatientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreatePatient() throws Exception {
        mockMvc.perform(post("/patients")
                .contentType(MediaType.APPLICATION_JSON).content(JsonStream.serialize(new PatientDto("Son", "Goku", "M",
                        "Namek", "0123", "1998-04-29"))))
                .andExpect(status().isCreated());
    }

}
