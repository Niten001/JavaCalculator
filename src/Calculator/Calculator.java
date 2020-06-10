package calculator;

import static java.lang.Double.NaN;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class Calculator {
    public enum BiOperatorModes {
        normal, add, minus, multiply, divide, power
    }

    public enum MonoOperatorModes {
        square, squareRoot, inverse, cos, sin, tan, log, rate, abs
    }

    private Double num1, num2;
    private BiOperatorModes mode = BiOperatorModes.normal;

    private Double calculateBiImpl() {
        switch(mode) {
            case normal:
                return num2;
            case add:
                return num1 + num2;
            case minus:
                return num1 - num2;
            case multiply:
                return num1 * num2;
            case divide:
                return num1 / num2;
            case power:
                return pow(num1,num2);
            default:
                // never reach
                throw new Error();
        }
    }

    public Double calculateBi(BiOperatorModes newMode, Double num) {
        if (mode == BiOperatorModes.normal) {
            num2 = 0.0;
            num1 = num;
            mode = newMode;
            return NaN;
        } else {
            num2 = num;
            num1 = calculateBiImpl();
            mode = newMode;
            return num1;
        }
    }

    public Double calculateEqual(Double num) {
        return calculateBi(BiOperatorModes.normal, num);
    }

    public Double reset() {
        num2 = 0.0;
        num1 = 0.0;
        mode = BiOperatorModes.normal;

        return NaN;
    }

    public Double calculateMono(MonoOperatorModes newMode, Double num) {
        switch(newMode) {
            case square:
                return num * num;
            case squareRoot:
                return Math.sqrt(num);
            case inverse:
                return 1 / num;
            case cos:
                return Math.cos(num);
            case sin:
                return Math.sin(num);
            case tan:
                if (num == 0 || num % 180 == 0) {
                    return 0.0;
                } else if (num % 90 == 0 && num % 180 != 0) {
                    return NaN;
                }
                return Math.tan(num);
            case log:
                return log10(num);
            case rate:
                return num / 100;
            case abs:
                return Math.abs(num);
            default:
                // never reach
                throw new Error();
        }
    }
}
