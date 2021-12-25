package report.assessing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import report.model.PatientInformation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AssessorTest {

    private Assessor assessor = new Assessor("src/test/resources/triggers");
    private PatientInformation patientInformation;

    @BeforeAll
    public void setup(){
        patientInformation = new PatientInformation();
        patientInformation.setPatientAge(35);
        patientInformation.setPatientSex("F");
    }

    @Test
    public void testObtainNumberOfTriggersFromNotes(){
        String[] triggersArray = {"hemoglobine A1C",
                "microalbumine",
                "taille",
                "poids",
                "fumeur",
                "anormal",
                "cholesterol",
                "vertige",
                "rechute",
                "reaction",
                "anticorps"};
        ArrayList<String> triggers = new ArrayList(List.of(triggersArray));
        assessor.obtainTriggers();

        while (!triggers.isEmpty()){
            assertEquals(triggers.size(), assessor.obtainNumberOfTriggerFromNotes(triggers));
            triggers.remove(0);
        }
    }

    @Test
    public void testObtainAssessmentShouldReturnNone(){
        List<String> patientNotes = List.of("");
        patientInformation.setNotes(patientNotes);
        assessor.setPatientInformation(patientInformation);
        assertEquals(Assessments.NONE, assessor.obtainAssessment());
    }

    @Test
    public void testObtainAssessmentShouldReturnBorderline(){
        List<String> patientNotes = List.of("taille", "poids");
        patientInformation.setNotes(patientNotes);
        assessor.setPatientInformation(patientInformation);
        assertEquals(Assessments.BORDERLINE, assessor.obtainAssessment());
    }

    @Test
    public void testObtainAssessmentShouldReturnInDanger(){
        List<String> patientNotes = List.of(
                "taille", "poids", "hémoglobine", "cholestérol",
                "vertige", "rechute", "réaction", "anticorps");
        patientInformation.setNotes(patientNotes);
        assessor.setPatientInformation(patientInformation);
        assertEquals(Assessments.IN_DANGER, assessor.obtainAssessment());
    }

    @Test
    public void testObtainAssessmentShouldReturnEarlyOnset(){
        patientInformation.setPatientAge(25);

        List<String> patientNotes = List.of(
                "taille", "poids", "hémoglobine", "cholestérol",
                "vertige", "rechute", "réaction", "anticorps");
        patientInformation.setNotes(patientNotes);
        assessor.setPatientInformation(patientInformation);
        assertEquals(Assessments.EARLY_ONSET, assessor.obtainAssessment());

        patientInformation.setPatientAge(35);
    }

}
