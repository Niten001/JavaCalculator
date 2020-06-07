package testingtool;

import java.util.*;
import javacalculator.*;

enum RelationTransform {
    PERMUTE, EQUAL, INEQUALITY
}

public class CalculatorTest {

    private UI uiCalculator;

    public CalculatorTest(UI uiCalculator) {
        this.uiCalculator = uiCalculator;
    }

    // Tests
    public void test1() {
        Random rand = new Random();
        rand.nextDouble();
        this.enterCalculatorInput(rand.nextDouble());
        uiCalculator.butAdd.doClick();
        this.enterCalculatorInput(rand.nextDouble());
        uiCalculator.butEqual.doClick();
        //System.out.println(this.getCalculatorOutput());
    }

    /**
    Runs numTests number of tests on the multiply function in the calculator.
    inRelation is the transformation to be done on the testing
    outputRelation is how the results are evaluated
    **/
    public int testMultiply(int numTests, RelationTransform inRelation, RelationTransform outputRelation) {
        int numPassed = 0;
        int a, b;
        Random rand = new Random();
        for(int i = 0; i < numTests; i++) {
            a = rand.nextInt();
            b = rand.nextInt();

            //Do a*b
            this.enterCalculatorInput(a);
            uiCalculator.butMultiply.doClick();
            this.enterCalculatorInput(b);
            uiCalculator.butEqual.doClick();
            double output1 = this.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do b*a
            this.enterCalculatorInput(b);
            uiCalculator.butMultiply.doClick();
            this.enterCalculatorInput(a);
            uiCalculator.butEqual.doClick();
            double output2 = this.getCalculatorOutput();

            //Metamorphic relation: a + b = b + a
            if(output1 == output2) {
                numPassed++;
            }
        }
        return numPassed;
    }

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

            /*Metamorphic relation: a/b > 1 if a > b and a/b < 1 if a < b
            First Case: a >= b then a/b >= 1
            Second Case: a < b, then a/b < 1
            Third Case: a > b, b < 0 then a/b < 1
            Fourth Case: a < b, b < 0, a < 0 then a/b > 1
            Fith Case: a < b, b < 0, a < 0 then a/b >= 1
            */
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

    /**
    Runs numTests number of tests on the logarithm function in the calculator.
    inRelation is the transformation to be done on the
    **/
    public int testLogarithm(int numTests, RelationTransform inRelation, RelationTransform outputRelation) {
        int numPassed = 0;
        int a, b;
        Random rand = new Random();
        for(int i = 0; i < numTests; i++) {
            a = rand.nextInt() & Integer.MAX_VALUE; //Positive numbers only for log
            b = rand.nextInt() & Integer.MAX_VALUE; //Positive numbers only for log

            //Do log(A)
            this.enterCalculatorInput(a);
            uiCalculator.butLog.doClick();
            double logA = this.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Do log(B)
            this.enterCalculatorInput(b);
            uiCalculator.butLog.doClick();
            double logB = this.getCalculatorOutput();
            uiCalculator.butCancel.doClick();

            //Metamorphic relation: log(a) > log(b) if a > b and log(b) > log(a) if b > a
            if(a >= b && logA >= logB || b > a && logB > logA) {
                numPassed++;
            }
        }
        return numPassed;
    }

    //Helper functions

    /**
    Enters a value into the calculator
    **/
    public void enterCalculatorInput(double num) {
        uiCalculator.text.setText(String.valueOf(num));
    }

    /**
    Returns whatever is currently displayed in the calculator output
    **/
    public double getCalculatorOutput() {
        return Double.valueOf(uiCalculator.text.getText());
    }

}
