package testingtool;

import java.util.*;
import calculator.*;

public class TrigTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    protected TrigTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Test Pythagorean Identity
        protected Map<String, String[][]> testPythagoreanIdentity(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < numAttempts; i++) {
                double randomDouble = rand.nextDouble();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butSin.doClick();
                double a1 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                this.testingTool.enterCalculatorInput(randomDouble);
                this.uiCalculator.butCos.doClick();
                double a2 = this.testingTool.getCalculatorOutput();
                this.uiCalculator.butCancel.doClick();

                // Rule: (sin(x))^2 + (cos(x))^2 = 1
                results[i] = this.testingTool.insertValues(
                    randomDouble,
                    (a1*a1 + a2*a2),
                    1,
                    this.testingTool.checkFuzzyEqual((a1*a1 + a2*a2), 1.0)
                );
            }

            output.put("Trigonometric function pythagorean identity", results);
            return output;
        }

        protected Map<String, String[][]> testPythagoreanIdentity() {
            return testPythagoreanIdentity(20);
        }

    //  Test Sum formula
        protected Map<String, String[][]> testSum(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[3*numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < (3*numAttempts); i += 3) {
                double x1 = rand.nextDouble();
                double x2 = rand.nextDouble();
                
                //  sin(x + y) = sin(x)cos(y) + cos(x)sin(y)
                    //  sin(x + y)
                        this.testingTool.enterCalculatorInput(x1 + x2);
                        this.uiCalculator.butSin.doClick();
                        double a1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                    //  sin(x)cos(y) + cos(x)sin(y)
                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butSin.doClick();
                        double s1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butCos.doClick();
                        double s2 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butCos.doClick();
                        double s3 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();
                        
                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butSin.doClick();
                        double s4 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        double a2 = s1*s2 + s3*s4;

                    results[i] = this.testingTool.insertValues(
                        x1,
                        x2,
                        a1,
                        a2,
                        this.testingTool.checkFuzzyEqual(a1, a2)
                    );

                //  cos(x + y) = cos(x)cos(y) - sin(x)sin(y)
                    //  cos(x + y)
                        this.testingTool.enterCalculatorInput(x1 + x2);
                        this.uiCalculator.butCos.doClick();
                        a1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                    //  cos(x)cos(y) - sin(x)sin(y)
                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butCos.doClick();
                        s1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butCos.doClick();
                        s2 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butSin.doClick();
                        s3 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();
                        
                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butSin.doClick();
                        s4 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        a2 = s1*s2 - s3*s4;

                    results[i+1] = this.testingTool.insertValues(
                        x1,
                        x2,
                        a1,
                        a2,
                        this.testingTool.checkFuzzyEqual(a1, a2)
                    );

                //  tan(x + y) = (tan(x) + tan(y))/(1 - tan(x)tan(y))
                    //  tan(x + y)
                        this.testingTool.enterCalculatorInput(x1 + x2);
                        this.uiCalculator.butTan.doClick();
                        a1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                    //  (tan(x) + tan(y))/(1 - tan(x)tan(y))
                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butTan.doClick();
                        s1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butTan.doClick();
                        s2 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        a2 = (s1 + s2)/(1 - s1*s2);

                    results[i+2] = this.testingTool.insertValues(
                        x1,
                        x2,
                        a1,
                        a2,
                        this.testingTool.checkFuzzyEqual(a1, a2)
                    );
            }

            output.put("Trigonometric function sum formula", results);
            return output;
        }

        protected Map<String, String[][]> testSum() {
            return testSum(10);
        }
    
    //  Test Difference formula
        protected Map<String, String[][]> testDifference(int numAttempts) {
            Map<String, String[][]> output = new HashMap<String, String[][]>();
            String[][] results = new String[3*numAttempts][5];
            Random rand = new Random();

            for (int i = 0; i < (3*numAttempts); i += 3) {
                double x1 = rand.nextDouble();
                double x2 = rand.nextDouble();
                
                //  sin(x - y) = sin(x)cos(y) - cos(x)sin(y)
                    //  sin(x - y)
                        this.testingTool.enterCalculatorInput(x1 - x2);
                        this.uiCalculator.butSin.doClick();
                        double a1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                    //  sin(x)cos(y) - cos(x)sin(y)
                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butSin.doClick();
                        double s1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butCos.doClick();
                        double s2 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butCos.doClick();
                        double s3 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();
                        
                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butSin.doClick();
                        double s4 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        double a2 = s1*s2 - s3*s4;

                    results[i] = this.testingTool.insertValues(
                        x1,
                        x2,
                        a1,
                        a2,
                        this.testingTool.checkFuzzyEqual(a1, a2)
                    );

                //  cos(x - y) = cos(x)cos(y) + sin(x)sin(y)
                    //  cos(x + y)
                        this.testingTool.enterCalculatorInput(x1 - x2);
                        this.uiCalculator.butCos.doClick();
                        a1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                    //  cos(x)cos(y) + sin(x)sin(y)
                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butCos.doClick();
                        s1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butCos.doClick();
                        s2 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butSin.doClick();
                        s3 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();
                        
                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butSin.doClick();
                        s4 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        a2 = s1*s2 + s3*s4;

                    results[i+1] = this.testingTool.insertValues(
                        x1,
                        x2,
                        a1,
                        a2,
                        this.testingTool.checkFuzzyEqual(a1, a2)
                    );

                //  tan(x - y) = (tan(x) - tan(y))/(1 + tan(x)tan(y))
                    //  tan(x - y)
                        this.testingTool.enterCalculatorInput(x1 - x2);
                        this.uiCalculator.butTan.doClick();
                        a1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                    //  (tan(x) - tan(y))/(1 + tan(x)tan(y))
                        this.testingTool.enterCalculatorInput(x1);
                        this.uiCalculator.butTan.doClick();
                        s1 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        this.testingTool.enterCalculatorInput(x2);
                        this.uiCalculator.butTan.doClick();
                        s2 = this.testingTool.getCalculatorOutput();
                        this.uiCalculator.butCancel.doClick();

                        a2 = (s1 - s2)/(1 + s1*s2);

                    results[i+2] = this.testingTool.insertValues(
                        x1,
                        x2,
                        a1,
                        a2,
                        this.testingTool.checkFuzzyEqual(a1, a2)
                    );
            }

            output.put("Trigonometric function difference formula", results);
            return output;
        }

        protected Map<String, String[][]> testDifference() {
            return testDifference(10);
        }
    
    //  Test All
        public Map<String, String[][]> testAll() {
            Map<String, String[][]> output = new HashMap<String, String[][]>();

            output.putAll(this.testPythagoreanIdentity());
            output.putAll(this.testSum());
            output.putAll(this.testDifference());

            return output;
        }
}