package report.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static report.util.StringNormalizer.normalize;

public class StringNormalizerTest {

    @Test
    public void testNormalize(){
        String stringToNormalize = "éèûìîà";
        assertEquals(normalize(stringToNormalize), "EEUIIA");
    }

}
