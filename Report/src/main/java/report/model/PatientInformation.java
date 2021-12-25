package report.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PatientInformation {

    private int patientAge;
    private List<String> notes;
    private String patientSex;

    public PatientInformation(){}

    public PatientInformation(Patient patient, List<Note> notes) {
        this.patientSex = patient.getSex();
        calculateAge(patient.getBirthdate());
        this.notes = notes.stream().map(Note::getContent).collect(Collectors.toList());
    }

    public void calculateAge(String birthdateString) {
        LocalDate birthdate = LocalDate.parse(birthdateString);
        patientAge = Period.between(birthdate, LocalDate.now()).getYears();
    }

}
