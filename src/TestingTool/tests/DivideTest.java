package testingtool;

import java.util.*;
import javacalculator.*;

public class DivideTest {
    private UI uiCalculator;
    private TestingTool testingTool;

    public DivideTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
        this.testingTool = new TestingTool(this.uiCalculator);
    }

    //  Not sure what you were trying to test here below is the function you had
    /*
        public int testDivide(int numTests, RelationTransform inRelation, RelationTransform outputRelation) {
            int numPassed = 0;
            int a, b;
            Random rand = new Random();
            for(int i = 0; i < numTests; i++) {
                a = rand.nextInt();
                b = rand.nextInt();

                //Do a / b
                this.enterCalculatorInput(a);
                uiCalculator.butDivide.doClick();
                this.enterCalculatorInput(b);
                uiCalculator.butEqual.doClick();
                double output1 = this.getCalculatorOutput();
                uiCalculator.butCancel.doClick();

                //Metamorphic relation: a/b > 1 if a > b and a/b < 1 if a < b
                //First Case: a >= b then a/b >= 1
                //Second Case: a < b, then a/b < 1
                //Third Case: a > b, b < 0 then a/b < 1
                //Fourth Case: a < b, b < 0, a < 0 then a/b > 1
                //Fith Case: a < b, b < 0, a < 0 then a/b >= 1
                
                if(a >= b && a/b >= 1 || a < b && a/b < 1 && b > 1 || a > b && a/b < 1 && b < 0 || a > b && b < 0 && a < 0 && a/b < 1 || a < b && b < 0 && a < 0 && a/b >= 1) {
                    numPassed++;
                }
                else
                {
                    System.out.println(a + " " + b + " " + (a/b));
                }
            }
            return numPassed;
        }
    */

    //  Test All
        public Map<String, boolean[]> testAll() {
            Map<String, boolean[]> output = new HashMap<String, boolean[]>();

            return output;
        }
}