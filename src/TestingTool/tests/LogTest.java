package testingtool;

import java.util.*;
import calculator.*;

public class LogTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected LogTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
    public Map<String, String[][]> testAlgebraicValues() {
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

        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[testedValues.length][5];

        for (int i = 0; i < testedValues.length; i++) {
            this.testingTool.enterCalculatorInput(testedValues[i][0]);
            this.uiCalculator.butLog.doClick();
            results[i] = this.testingTool.insertValues(
                testedValues[i][0],
                testedValues[i][1],
                this.testingTool.getCalculatorOutput(),
                this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
            );
            this.uiCalculator.butCancel.doClick();
        }

        output.put("Logarithm function specific values", results);
        return output;
    }

    //  Test Scalar
    protected Map<String, String[][]> testScalar(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for (int i = 0; i < numAttempts; i++) {
            double a = (double)(rand.nextInt(Integer.MAX_VALUE - 1)) + rand.nextDouble(); //  Positive numbers only for log
            double b = (double)(rand.nextInt(Integer.MAX_VALUE - 1)) + rand.nextDouble(); //  Positive numbers only for log

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
            results[i] = this.testingTool.insertValues(
                a,
                b,
                logA,
                logB,
                (a >= b && logA >= logB || b > a && logB > logA)
            );
        }

        output.put("Logarithm function Scalar", results);
        return output;
    }

    protected Map<String, String[][]> testScalar() {
        return testScalar(20);
    }

    //  Test Product
    protected Map<String, String[][]> testProduct(int numAttempts) {
        Map<String, String[][]> output = new HashMap<String, String[][]>();
        String[][] results = new String[numAttempts][5];
        Random rand = new Random();

        for (int i = 0; i < numAttempts; i++) {
            double a = (double)(rand.nextInt(Integer.MAX_VALUE - 1)) + rand.nextDouble(); //  Positive numbers only for log
            double b = (double)(rand.nextInt(Integer.MAX_VALUE - 1)) + rand.nextDouble(); //  Positive numbers only for log
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
            results[i] = this.testingTool.insertValues(
                a,
                b,
                logA + logB,
                logAB,
                this.testingTool.checkFuzzyEqual(logA + logB, logAB)
            );
        }

        output.put("Logarithm function Product", results);
        return output;
    }

    protected Map<String, String[][]> testProduct() {
        return testProduct(20);
    }

    //  Test All
    public Map<String, String[][]> testAll() {
        Map<String, String[][]> output = new HashMap<String, String[][]>();

        output.putAll(this.testAlgebraicValues());
        output.putAll(this.testScalar());
        output.putAll(this.testProduct());
        
        return output;
    }
}
