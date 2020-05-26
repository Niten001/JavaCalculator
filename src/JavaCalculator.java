package javacalculator;

public class JavaCalculator {
    public static void main(String[] args) {
        try {
            UI uiCalculator = new UI();
            uiCalculator.init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}