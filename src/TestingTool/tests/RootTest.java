package testingtool;

import java.util.*;
import javacalculator.*;

public class RootTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected RootTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected boolean[] testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 4, 2 },
                { 64, 8 },
                { 16, 4 },
                { 17, Math.sqrt(17)},
                { 0, 0 },
                { 3, Math.sqrt(3) },
                { 100, 10 },
                { 233, Math.sqrt(233)},
                { 7649, Math.sqrt(7649) }
            };

            boolean[] numPassed = new boolean[testedValues.length];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butSquareRoot.doClick();
                numPassed[i] = this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1]);
                this.uiCalculator.butCancel.doClick();
            }

            return numPassed;
        }

    //  Test Root Squared
        protected boolean[] testRootSquared(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //No negative values for square root

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butSquareRoot.doClick();
                double root = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                //Rule: Root(x) * Root(x) = x
                numPassed[i] = this.testingTool.checkFuzzyEqual(root * root, randomDouble);
            }

            return numPassed;
        }

        protected boolean[] testRootSquared() {
            return testRootSquared(20);
        }

    //  Test Product
        protected boolean[] testProduct(int numAttempts) {
            boolean[] numPassed = new boolean[numAttempts];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble1 = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //No negative values for square root
                double randomDouble2 = rand.nextInt(Integer.MAX_VALUE - 1) + rand.nextDouble(); //No negative values for square root

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

                //Rule: Product of Square Roots - Root(xy) = Root(x) * Root(y)
                numPassed[i] = this.testingTool.checkFuzzyEqual(a1 * a2, a3);
            }

            return numPassed;
        }

        protected boolean[] testProduct() {
            return testProduct(20);
        }
    
    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            output.put("Square Root function specific values", this.testAlgebraicValues());
            output.put("Square Root function root squared", this.testRootSquared());
            output.put("Square Root function product", this.testProduct());

            return output;
        }
}