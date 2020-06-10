package testingtool;

import java.util.*;
import calculator.*;

public class SquareTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected SquareTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Specific Algebraic Values
        protected Map<String, String[][]> testAlgebraicValues() {
            //  Array of [Values to be tested, expected results]
            double[][] testedValues = {
                { 2, 4 },
                { 4, 16 },
                { -5.78, Math.pow(-5.78, 2) },
                { -17, Math.pow(-17, 2) },
                { 0, Math.pow(0, 2) },
                { -7, Math.pow(-7, 2) },
                { 10, Math.pow(10, 2) },
                { 632, Math.pow(632, 2) },
                { 4973, Math.pow(4973, 2) }
            };

            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[testedValues.length][5];

            for (int i = 0; i < testedValues.length; i++) {
                this.testingTool.enterCalculatorInput(testedValues[i][0]);
                this.uiCalculator.butSquare.doClick();
                results[i] = this.testingTool.insertValues(
                    testedValues[i][0],
                    testedValues[i][1],
                    this.testingTool.getCalculatorOutput(),
                    this.testingTool.checkFuzzyEqual(this.testingTool.getCalculatorOutput(), testedValues[i][1])
                );
                this.uiCalculator.butCancel.doClick();
            }

            output.put("Square function specific values", results);
            return output;
        }

    //  Test Positive Square
        protected Map<String, String[][]> testPositiveSquare(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                int randomNum = rand.nextInt();

                this.testingTool.enterCalculatorInput(randomNum);
                this.uiCalculator.butSquare.doClick();
                double square = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: A square of a number should always be positive
                results[i] = this.testingTool.insertValues(
                    randomNum,
                    square,
                    0,
                    (square >= 0)
                );
            }

            output.put("Square function positive square", results);
            return output;
        }

        protected Map<String, String[][]> testPositiveSquare() {
            return testPositiveSquare(20);
        }

    //  Test Identity
        protected Map<String, String[][]> testIdentity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                int randomNum = rand.nextInt();

                this.testingTool.enterCalculatorInput(randomNum);
                this.uiCalculator.butSquare.doClick();
                double square1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(-1 * randomNum);
                this.uiCalculator.butSquare.doClick();
                double square2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: Property of squares - (x)^2 = (-x)^2
                results[i] = this.testingTool.insertValues(
                    randomNum,
                    square1,
                    square2,
                    this.testingTool.checkFuzzyEqual(square1, square2)
                );
            }

            output.put("Square function identity", results);
            return output;
        }

        protected Map<String, String[][]> testIdentity() {
            return testIdentity(20);
        }

    
    
    //  Test All
        public Map<String, String[][]> testAll() {
            Map<String, String[][]> output = new HashMap<String, String[][]>();

            output.putAll(this.testAlgebraicValues());
            output.putAll(this.testPositiveSquare());
            output.putAll(this.testIdentity());

            return output;
        }
}