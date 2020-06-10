package testingtool;

import java.util.*;
import calculator.*;

public class RateTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public RateTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    // Rate Multiply Test
    public Map<String, String[][]> testMultiply(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random random = new Random();

        for(int i = 0; i < numAttempts; i++) {
            double x = random.nextInt(100); // Randomize input value

            this.testingTool.enterCalculatorInput(x);
            this.uiCalculator.butRate.doClick();
            double rateOutput = this.testingTool.getCalculatorOutput();
            this.uiCalculator.butCancel.doClick();

            // Check
            results[i] = this.testingTool.insertValues(
                x,
                x,
                rateOutput * 100,
                this.testingTool.checkFuzzyEqual(rateOutput * 100, x)
            );
        }

        output.put("Rate function multiply", results);
        return output;
    }

    public Map<String, String[][]> testMultiply() {
        return testMultiply(20);
    }

    public Map<String, String[][]> testBetweenValues(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random random = new Random();

        for(int i = 0; i < numAttempts; i++) {
            double x = random.nextInt(100); // Randomize value between 0-100

            this.testingTool.enterCalculatorInput(x);
            this.uiCalculator.butRate.doClick();
            double rateOutput = this.testingTool.getCalculatorOutput();
            this.uiCalculator.butCancel.doClick();
            
            // Check if between 0 and 1
            results[i] = this.testingTool.insertValues(
                x,
                x/100,
                rateOutput,
                (rateOutput >= 0 && rateOutput <= 1)
            );
        }

        output.put("Rate function between values", results);
        return output;
    }

    public Map<String, String[][]> testBetweenValues() {
        return testBetweenValues(20);
    }

    // Test All
    public Map<String, String[][]> testAll() {
        Map<String, String[][]> output = new HashMap<String, String[][]>();

        output.putAll(this.testMultiply());
        output.putAll(this.testBetweenValues());

        return output;
    }
}