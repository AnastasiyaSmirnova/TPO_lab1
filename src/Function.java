import static java.lang.Math.*;

public class Function {

    public static double arccos(double x) throws IncorrectInputArgument {
        /*
         * f(x) = arccos(x), D(x)=[-1; 1];  f(x) = f(a) + f'(a)(x-a) / 1! + f''(a)(x-a)^2 / 2! + ...
         * a = 0
         * */

        if (x < -1 || x > 1 || Double.isNaN(x)) {
            throw new IncorrectInputArgument("Incorrect argument. Allowed values: [-1; 1]");
        }
        double esp = 1;
        double result = PI * 0.5 - x;

        double numerator = 1;
        double denominator = 1;

        int n = 1;
        while (esp > pow(10, -7)) {
            double lastResult = result;
            numerator *= (2 * n - 1) * pow(x, 2 * n + 1);
            denominator *= pow(2 * n * (2 * n + 1), -1);
            result -= numerator * denominator;
            n++;
            esp = abs(lastResult - result);
        }
        return result;
//        return acos(x);
    }
}
