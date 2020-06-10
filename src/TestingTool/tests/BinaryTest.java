package testingtool;

import java.util.*;
import calculator.*;

public class BinaryTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public BinaryTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    // Binary Validity
    public Map<String, String[][]> testValidity(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            String input = "";
            int x = rand.nextInt(5) + 1;

            // Binary Value Test
            for (int j = 0; j < x; j++) {
                int y = rand.nextInt(9);
                input += y;
                uiCalculator.but[y].doClick();
            }
            uiCalculator.butBinary.doClick();
            String binOutput = uiCalculator.text.getText();
            uiCalculator.butCancel.doClick();
            char[] binArray = binOutput.toCharArray();
            boolean check = true;

            for (char c : binArray) {
                if (c != '0' && c != '1') {
                    check = false;
                }
            }
            
            results[i] = this.testingTool.insertValues(
                Double.parseDouble(input),
                Integer.toBinaryString(Integer.parseInt(input)),
                binOutput,
                check
            );
        }

        output.put("Binary function validity", results);
        return output;
    }

    public Map<String, String[][]> testValidity() {
        return testValidity(20);
    }

    // Test All
    public Map<String, String[][]> testAll() {
        Map<String, String[][]> output = new HashMap<String, String[][]>();

        output.putAll(this.testValidity());

        return output;
    }
}
