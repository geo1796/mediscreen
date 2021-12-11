package notes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
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
    @NotBlank(message = "date is mandatory")
    private String date;

}
