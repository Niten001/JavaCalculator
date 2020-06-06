package testingtool;

import java.util.*;
import javacalculator.*;

enum RelationTransform {
    PERMUTE, EQUAL
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
    inRelation is the transformation to be done on the
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

            if(output1 == output2) {
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
        return Double.parseDouble(uiCalculator.text.getText());
    }

}
