package testingtool;

import java.util.*;
import calculator.*;

public class AbsTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public AbsTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    // Test Non-Negativity
    public Map<String, String[][]> testNonNegative(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int x = rand.nextInt(); // Randomize input value

            // abs(x)
            this.testingTool.enterCalculatorInput(x);
            uiCalculator.butAbs.doClick();
            double absOutput = this.testingTool.getCalculatorOutput();

            // Check
            results[i] = this.testingTool.insertValues(
                x,
                ((x < 0) ? -x : x),
                absOutput,
                (absOutput >= 0)
            );
        }
        
        output.put("Absolute function non-negativity", results);
        return output;
    }

    public Map<String, String[][]> testNonNegative() {
        return testNonNegative(20);
    }

    // Test Evenness
    public Map<String, String[][]> testEvenness(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int x = rand.nextInt(); // Randomize input value

            // abs(x)
            this.testingTool.enterCalculatorInput(x);
            uiCalculator.butAbs.doClick();
            double absOutput1 = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            // abs(-x)
            this.testingTool.enterCalculatorInput(-1*x);
            uiCalculator.butAbs.doClick();
            double absOutput2 = this.testingTool.getCalculatorOutput();

            // Check [x] = [-x]
            results[i] = this.testingTool.insertValues(
                x,
                absOutput1,
                absOutput2,
                this.testingTool.checkFuzzyEqual(absOutput1, absOutput2)
            );
        }

        output.put("Absolute function evenness", results);
        return output;
    }

    public Map<String, String[][]> testEvenness() {
        return testEvenness(20);
    }

    // Test All
    public Map<String, String[][]> testAll() {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        
        output.putAll(this.testNonNegative());
        output.putAll(this.testEvenness());

        return output;
    }
}
