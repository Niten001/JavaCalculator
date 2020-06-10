package testingtool;

import java.util.*;
import javacalculator.*;

public class BinaryTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public BinaryTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    // Binary Validity
    public boolean[] testValidity(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int x = rand.nextInt(5) + 1;

            // Binary Value Test
            for (int j = 0; j < x; j++) {
                int y = rand.nextInt(9);
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
            numPassed[i] = check;
        }

        return numPassed;
    }

    public boolean[] testValidity() {
        return testValidity(20);
    }

    // Test All
    public Map<String, boolean[]> testAll() {
        Map<String, boolean[]> output = new HashMap<String, boolean[]>();

        output.put("Binary function validity", this.testValidity());

        return output;
    }
}
