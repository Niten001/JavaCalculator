package testingtool;

import java.util.*;
import javacalculator.*;

public class AbsTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public AbsTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    // Test Non-Negativity
    public boolean[] testNonNegative(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for(int i = 0; i < numAttempts; i++) {
            int x = rand.nextInt(); // Randomize input value

            // abs(x)
            this.testingTool.enterCalculatorInput(x);
            uiCalculator.butAbs.doClick();
            /* uiCalculator.butEqual.doClick(); */
            double absOutput = this.testingTool.getCalculatorOutput();

            // Check
            numPassed[i] = (absOutput >= 0);
        }

        return numPassed;
    }

    public boolean[] testNonNegative() {
        return testNonNegative(20);
    }

    // Test Evenness
    public boolean[] testEvenness(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
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
            numPassed[i] = this.testingTool.checkFuzzyEqual(absOutput1, absOutput2);
        }
        return numPassed;
    }

    public boolean[] testEvenness() {
        return testEvenness(20);
    }

    // Test All
    public Map<String, boolean[]> testAll() {
        Map<String, boolean[]> output = new HashMap<String, boolean[]>();

        output.put("Abs function non-negativity:", this.testNonNegative());
        output.put("Abs function evenness:", this.testEvenness());

        return output;
    }
}
