package report.util;

import java.text.Normalizer;

public class StringNormalizer {

    public static String normalize(String stringToNormalize) {
        return Normalizer.normalize(stringToNormalize, Normalizer.Form.NFD) // to get rid
                .replaceAll("\\p{M}", "") // of accented characters
                .toUpperCase();
    }

}
