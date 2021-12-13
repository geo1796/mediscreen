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
    @NotBlank(message = "content is mandatory")
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[ noteDto : patientId = {"
                        + this.patientId + "} ; content = {"
                        + this.content + "} ]";
    }
}
