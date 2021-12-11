package notes.controller;

import com.jsoniter.output.JsonStream;
import notes.dto.NoteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testAddNoteOk() throws Exception {
        mockMvc.perform(post("/patHistory/add")
                .contentType(MediaType.APPLICATION_JSON).content(
                        JsonStream.serialize(new NoteDto(
                                1L, "Son", "Goku",
                                "this is a note content", "2021-12-12"))))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddNotValidNote() throws Exception {
        mockMvc.perform(post("/patHistory/add")
                .contentType(MediaType.APPLICATION_JSON).content(
                        JsonStream.serialize(new NoteDto())))
                .andExpect(status().isUnprocessableEntity());
    }
}