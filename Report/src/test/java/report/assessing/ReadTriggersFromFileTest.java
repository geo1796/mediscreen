package report.assessing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static report.util.StringNormalizer.normalize;

public class ReadTriggersFromFileTest {

    @Test
    public void testGetTriggers() {
        String[] triggers = {"hemoglobine A1C",
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

        ReadTriggersFromFile classUnderTest = new ReadTriggersFromFile("src/test/resources/triggers");
        List<String> result = classUnderTest.getTriggers();

        for (String trigger: triggers){
            assertTrue(result.contains(normalize(trigger)));
        }

        assertEquals(triggers.length, result.size());
    }

}
