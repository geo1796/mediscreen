package Report.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientInformation {

    private int patientAge;
    private List<String> notes;
    private String patientSex;

}
