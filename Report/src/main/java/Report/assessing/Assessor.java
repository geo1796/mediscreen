package Report.assessing;

import Report.model.PatientInformation;
import Report.model.Report;

import java.util.List;
import java.util.Objects;

public class Assessor {

    private List<String> triggers;
    private PatientInformation patientInformation;

    public Report generateReport(){
        return new Report(obtainAssessment(), patientInformation.getPatientAge(), patientInformation.getPatientSex());
    }

    private String obtainAssessment() {
        obtainTriggers();
        int numberOfTriggerInNotes = obtainNumberOfTriggerFromNotes(patientInformation.getNotes());
        String result;

        if (patientInformation.getPatientAge() >= 30 && numberOfTriggerInNotes >= 8)
        { // patient is over 30 with 8 or more triggers
            return Assessments.EARLY_ONSET;
        }

        else if (
                Objects.equals(patientInformation.getPatientSex(), "F")
                        && patientInformation.getPatientAge() < 30
                        && numberOfTriggerInNotes >= 7)
        { // patient is a woman less than 30 and has 4 or more triggers
            result = Assessments.EARLY_ONSET;
        }

        else if (
                Objects.equals(patientInformation.getPatientSex(), "M")
                        && patientInformation.getPatientAge() < 30
                        && numberOfTriggerInNotes >= 5)
        { // patient is a man less than 30 and has 5 or more triggers
            result = Assessments.EARLY_ONSET;
        }

        else if (
                Objects.equals(patientInformation.getPatientSex(), "M")
                        && patientInformation.getPatientAge() < 30
                        && numberOfTriggerInNotes >= 3)
        { // patient is a man less than 30 and has 3 or more triggers
            result = Assessments.IN_DANGER;
        }
        else if (
                Objects.equals(patientInformation.getPatientSex(), "F")
                        && patientInformation.getPatientAge() < 30
                        && numberOfTriggerInNotes >= 4)
        { // patient is a woman less than 30 and has 4 or more triggers
            result = Assessments.IN_DANGER;
        }

        else if (patientInformation.getPatientAge() >= 30 && numberOfTriggerInNotes >= 6)
        { // patient is over 30 with 6 or more triggers
            result = Assessments.IN_DANGER;
        }

        else if (numberOfTriggerInNotes >= 2 && patientInformation.getPatientAge() >= 30)
        { // patient is over 30 and has 2 or more triggers in his notes
            return Assessments.BORDERLINE;
        }

        else if (numberOfTriggerInNotes == 0)
        {
            return Assessments.NONE;
        }

        return ""; // ?
    }

    private void obtainTriggers() {
        ReadTriggersFromFile readTriggersFromFile = new ReadTriggersFromFile("Rapport/src/main/resources/triggers");
        this.triggers = readTriggersFromFile.getTriggers();
    }

    private int obtainNumberOfTriggerFromNotes(List<String> notes){
        int numberOfTriggerInNotes = 0;

        for (String trigger: this.triggers){
            for (String note: notes){
                if (note.contains(trigger));
                numberOfTriggerInNotes++;
                this.triggers.remove(trigger);
            }
        }

        return numberOfTriggerInNotes;
    }

}
