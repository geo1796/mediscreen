package Report.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Report {

    private String assessment;
    private int patientAge;
    private String patientSex;

    @Override
    public String toString() {
        return "Patient : [ age = " + patientAge
                + "; sex = " + patientSex + " ] "
                + " diabetes assessment is " + assessment;
    }

}
