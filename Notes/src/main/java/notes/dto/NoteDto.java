package notes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NoteDto {

    @NotNull(message = "patientId is mandatory")
    private long patientId;
    @NotBlank(message = "patientLastName is mandatory")
    private String patientLastName;
    @NotBlank(message = "patientFirstName is mandatory")
    private String patientFirstName;
    @NotBlank(message = "content is mandatory")
    private String content;



    public void setContent(String content) {
        this.content = content;
    }
}
