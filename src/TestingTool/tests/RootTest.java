package testingtool;

import java.util.*;
import calculator.*;

public class RootTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected RootTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected Map<String, String[][]> testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 4, 2 },
                { 64, 8 },
                { 16, 4 },
                { 17, Math.sqrt(17) },
                { 0, 0 },
                { 3, Math.sqrt(3) },
                { 100, 10 },
                { 233, Math.sqrt(233) },
                { 7649, Math.sqrt(7649) }
            };

            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butSquareRoot.doClick();
                results[i] = this.testingTool.insertValues(
                    testedValues[i][0],
                    testedValues[i][1],
                    this.testingTool.getCalculatorOutput(),
                    this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
                );
                this.uiCalculator.butCancel.doClick();
            }

            output.put("Square Root function specific values", results);
            return output;
        }

    //  Test Root Squared
        protected Map<String, String[][]> testRootSquared(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = (double)rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); // No negative values for square root

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butSquareRoot.doClick();
                double root = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: Root(x) * Root(x) = x
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    root * root,
                    randomDouble,
                    this.testingTool.checkFuzzyEqual(root * root, randomDouble)
                );
            }

            output.put("Square Root function root squared", results);
            return output;
        }

        protected Map<String, String[][]> testRootSquared() {
            return testRootSquared(20);
        }

    //  Test Product
        protected Map<String, String[][]> testProduct(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble1 = (double)rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //No negative values for square root
                double randomDouble2 = (double)rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //No negative values for square root

                this.testingTool.enterCalculatorInput(randomDouble1);
                this.uiCalculator.butSquareRoot.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble2);
                this.uiCalculator.butSquareRoot.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble1 * randomDouble2);
                this.uiCalculator.butSquareRoot.doClick();
                double a3 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: Product of Square Roots - Root(xy) = Root(x) * Root(y)
                results[i] = this.testingTool.insertValues(
                    randomDouble1,
                    randomDouble2,
                    a1 * a2,
                    a3,
                    this.testingTool.checkFuzzyEqual(a1 * a2, a3)
                );
            }

            output.put("Square Root function product", results);
            return output;
        }

        protected Map<String, String[][]> testProduct() {
            return testProduct(20);
        }
    
    //  Test All
        public Map<String, String[][]> testAll() {
            Map<String, String[][]> output = new HashMap<String, String[][]>();

            output.putAll(this.testAlgebraicValues());
            output.putAll(this.testRootSquared());
            output.putAll(this.testProduct());

            return output;
        }
}