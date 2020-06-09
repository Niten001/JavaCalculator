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
                Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueExceptionHandler());

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
class EventQueueExceptionHandler extends EventQueue {

    protected void dispatchEvent(AWTEvent newEvent) {
        try {
            super.dispatchEvent(newEvent);
        } catch (Throwable t) {}
    }
}
