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

    //  Test Specific Algebraic Values
    public boolean[] testAlgebraicValues() {
        //  Array of [Values to be tested, expected results]
        double[][] testedValues = {
            { 1, 0 },
            { 10, 1 },
            { 100, 2 },
            { 1000, 3 },
            { 6.7, Math.log10(6.7) },
            { 12424, Math.log10(12424) },
            { 0.2, Math.log10(0.2) },
            { 78, Math.log10(78) },
            { 13, Math.log10(13) }
        };

        boolean[] numPassed = new boolean[testedValues.length];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butLog.doClick();
            numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1]);
            this.uiCalculator.butCancel.doClick();
        }

        return numPassed;
    }

    //  Test Scalar
    protected boolean[] testScalar(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for (int i = 0; i < numAttempts; i++) {
            double a = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //  Positive numbers only for log
            double b = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //  Positive numbers only for log

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

    //  Test Product
    protected boolean[] testProduct(int numAttempts) {
        boolean[] numPassed = new boolean[numAttempts];
        Random rand = new Random();

        for (int i = 0; i < numAttempts; i++) {
            double a = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //  Positive numbers only for log
            double b = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //  Positive numbers only for log
            double ab = a * b;

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

            //  Do log(AB)
            this.testingTool.enterCalculatorInput(ab);
            uiCalculator.butLog.doClick();
            double logAB = this.testingTool.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //  Product relation: log(AB) = log(A) + log(B)
            numPassed[i] = this.testingTool.checkFuzzyEqual(logA + logB, logAB);
        }

        return numPassed;
    }

    protected boolean[] testProduct() {
        return testProduct(20);
    }

    //  Test All
    public Map<String, boolean[]> testAll() {
        Map<String, boolean[]> output = new HashMap<String, boolean[]>();

        output.put("Log function specific values", this.testAlgebraicValues());
        output.put("Logarithm function Scalar", this.testScalar());
        output.put("Logarithm function Product", this.testProduct());

        return output;
    }
}
