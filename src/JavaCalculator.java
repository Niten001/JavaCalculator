package javacalculator;

import testingtool.*;
import java.awt.*;

public class JavaCalculator {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            try {
                UI uiCalculator = new UI();
                uiCalculator.init();

                //Exception handler for AWT-EventQueue errors
<<<<<<< HEAD
                Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueExceptionHandler());
=======
                Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueProxy());
>>>>>>> Completed MultiplyTest.java

                // Instantiate testing tool
                TestingTool test = new TestingTool(uiCalculator);
                test.runTests();

                // Exit after running tests
                System.exit(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                UI uiCalculator = new UI();
                uiCalculator.init();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

/**
Handle AWT EventQueue exceptions
**/
<<<<<<< HEAD
class EventQueueExceptionHandler extends EventQueue {
=======
class EventQueueProxy extends EventQueue {
>>>>>>> Completed MultiplyTest.java

    protected void dispatchEvent(AWTEvent newEvent) {
        try {
            super.dispatchEvent(newEvent);
<<<<<<< HEAD
        } catch (Throwable t) {}
=======
        } catch (Throwable t) {
            System.out.println("");
        }
>>>>>>> Completed MultiplyTest.java
    }
}
