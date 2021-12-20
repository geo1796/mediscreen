package Report.assessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ReadTriggersFromFile {

    private final String filepath;

    public ReadTriggersFromFile (String filepath) {
        this.filepath = filepath;
    }

    public List<String> getTriggers()
    {
        List<String> result = new ArrayList<>();

        if (filepath != null)
        {
            try
            {
                BufferedReader reader = new BufferedReader (new FileReader(filepath));
                String line = reader.readLine();

                while (line != null) {
                    //result.add(line.toUpperCase());
                    result.add(
                            Normalizer.normalize(line, Normalizer.Form.NFD) // to get rid
                                    .replaceAll("\\p{M}", "") // of accented characters
                                    .toUpperCase());
                    line = reader.readLine();
                }
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return result;
    }
}
