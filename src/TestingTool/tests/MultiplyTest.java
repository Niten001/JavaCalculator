package testingtool;

import java.util.*;
import javacalculator.*;

public class MultiplyTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public MultiplyTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Commutativity
        public boolean[] testCommutativity(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for(int i = 0; i < numAttempts; i++) {
                int a = rand.nextInt();
                int b = rand.nextInt();

                //Do a*b
                this.testingTool.enterCalculatorInput(a);
                uiCalculator.butMultiply.doClick();
                this.testingTool.enterCalculatorInput(b);
                uiCalculator.butEqual.doClick();
                double output1 = this.testingTool.getCalculatorOutput();
                uiCalculator.butCancel.doClick();

                //Do b*a
                this.testingTool.enterCalculatorInput(b);
                uiCalculator.butMultiply.doClick();
                this.testingTool.enterCalculatorInput(a);
                uiCalculator.butEqual.doClick();
                double output2 = this.testingTool.getCalculatorOutput();

                //  Commutativity relation: a * b = b * a
                numPassed[i] = this.testingTool.checkFuzzyEqual(output1, output2);
            }

            return numPassed;
        }

        public boolean[] testCommutativity() {
            return testCommutativity(20);
        }

    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            output.put("Multiply function commutativity", this.testCommutativity());

            return output;
        }
}