package notes.controller;

import com.jsoniter.output.JsonStream;
import notes.dto.NoteDto;
import notes.model.Note;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NoteControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() throws IOException {
        Note note = new Note(1L, "this is a note about Son Goku");
        note.setId("123");
        mongoTemplate.insert(note, "note");
    }

    @AfterEach
    public void cleanup() throws IOException {
        mongoTemplate.dropCollection("note");
    }

    @Test
    public void testAddNoteOk() throws Exception {
        mockMvc.perform(post("/patHistory/add")
                .contentType(MediaType.APPLICATION_JSON).content(
                        JsonStream.serialize(new NoteDto(
                                2L,"this is a note content"))))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddNotValidNote() throws Exception {
        mockMvc.perform(post("/patHistory/add")
                .contentType(MediaType.APPLICATION_JSON).content(
                        JsonStream.serialize(new NoteDto())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetAllNotes() throws Exception {
        mockMvc.perform(get("/patHistory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andDo(print());
    }

    @Test
    public void testGetNoteByPatientId() throws Exception {
        mockMvc.perform(get("/patHistory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content", is("this is a note about Son Goku")))
                .andDo(print());
    }

    @Test
    public void testGetNoteById() throws Exception {
        mockMvc.perform(get("/patHistory/note/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("this is a note about Son Goku")));
    }

    @Test
    public void testGetNotExistingNoteById() throws Exception {
        mockMvc.perform(get("/patHistory/note/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateNoteOk() throws Exception {
        NoteDto noteDto = new NoteDto(
                1L, "this is a new note content");

        mockMvc.perform(put("/patHistory/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonStream.serialize(noteDto)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patHistory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content", is("this is a new note content")))
                .andDo(print());
    }

    @Test
    public void testUpdateNotExistingNote() throws Exception {
        NoteDto noteDto = new NoteDto(1L, "...");
        mockMvc.perform(put("/patHistory/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStream.serialize(noteDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateInvalidNote() throws Exception {
        mockMvc.perform(put("/patHistory/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonStream.serialize(new NoteDto())))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDeleteNoteOk() throws Exception {
        mockMvc.perform(delete("/patHistory/note/123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNotExistingNote() throws Exception {
        mockMvc.perform(delete("/patHistory/note/0"))
                .andExpect(status().isNotFound());
    }
}