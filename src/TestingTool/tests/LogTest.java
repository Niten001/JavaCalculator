package testingtool;

import java.util.*;
import javacalculator.*;

public class LogTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected LogTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Scalar
        protected boolean[] testScalar(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                int a = rand.nextInt(Integer.MAX_VALUE); //  Positive numbers only for log
                int b = rand.nextInt(Integer.MAX_VALUE); //  Positive numbers only for log

                //  Do log(A)
                this.testingTool.enterCalculatorInput(a);
                uiCalculator.butLog.doClick();
                double logA = this.testingTool.getCalculatorOutput();
                uiCalculator.butCancel.doClick();

                //  Do log(B)
                this.testingTool.enterCalculatorInput(b);
                uiCalculator.butLog.doClick();
                double logB = this.testingTool.getCalculatorOutput();
                uiCalculator.butCancel.doClick();

                //  Scalar relation: log(a) > log(b) if a > b and log(b) > log(a) if b > a
                numPassed[i] = (a >= b && logA >= logB || b > a && logB > logA);
            }

            return numPassed;
        }

        protected boolean[] testScalar() {
            return testScalar(20);
        }
    
    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            output.put("Logarithm function Scalar", this.testScalar());

            return output;
        }
}