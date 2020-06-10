import static org.junit.Assert.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

import calculator.*;
import testingtool.*;

public class UnitTests {
    @Test
    public void testFuzzyEqual() {
        try {
            UI uiCalculator = new UI();
            uiCalculator.init();

            TestingTool test = new TestingTool(uiCalculator);
            Random rand = new Random();
            for (int i = 0; i < 100; i++) {
                double a = rand.nextDouble();
                double b = a + (rand.nextDouble()/10000000);
                assertTrue(test.checkFuzzyEqual(a, b));

                a = rand.nextDouble();
                b = a + ((double)rand.nextInt()/1000);
                assertTrue(!test.checkFuzzyEqual(a, b));

                a = rand.nextDouble();
                b = a;
                assertTrue(test.checkFuzzyEqual(a, b));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInputOutput() {
        try {
            UI uiCalculator = new UI();
            uiCalculator.init();

            TestingTool test = new TestingTool(uiCalculator);
            Random rand = new Random();
            
            for (int i = 0; i < 100; i++) {
                double a = rand.nextDouble();
                test.enterCalculatorInput(a);

                double b = test.getCalculatorOutput();

                assertTrue(a == b);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testOutputFileExists() {
        try {
            UI uiCalculator = new UI();
            uiCalculator.init();

            TestingTool test = new TestingTool(uiCalculator);
            test.generateOutputFile();

            File outputFile = new File("output.txt");
            boolean exists = outputFile.exists();
            assertTrue(exists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testOutputFileFilled() {
        try {
            UI uiCalculator = new UI();
            uiCalculator.init();

            TestingTool test = new TestingTool(uiCalculator);
            test.runTests();

            File outputFile = new File("output.txt");
            FileInputStream outputFileInputStream = new FileInputStream(outputFile);
            byte[] byteArray = new byte[(int)outputFile.length()];
            outputFileInputStream.read(byteArray);
            String data = new String(byteArray);
            String[] stringArray = data.split("\n");
            int outputFileLength = stringArray.length;
            System.out.println(outputFileLength);
            assertTrue(outputFileLength > 800);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}