package javacalculator;

public class JavaCalculator {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("test")) {
            try {
                UI uiCalculator = new UI();
                uiCalculator.init();
                
                Test.runTests(uiCalculator);
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