package report.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Report {

    private String assessment;
    private int patientAge;
    private String patientSex;

    @Override
    public String toString() {
        return "Patient : [ age = " + patientAge
                + " , sex = " + patientSex + " ] "
                + " diabetes assessment is " + assessment;
    }

}
