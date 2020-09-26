import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class Function {
    public static double arccos(double x) throws IncorrectInputArgument {
        /*
         * f(x) = arccos(x), D(x)=[-1; 1];  f(x) = f(a) + f'(a)(x-a) / 1! + f''(a)(x-a)^2 / 2! + ...
         * a = 0
         * */

        if (x < -1 || x > 1) {
            throw new IncorrectInputArgument("Incorrect argument. Allowed values: [-1; 1]");
        }
        return PI / 2 - x - pow(x, 3) / 6 - (3 * pow(x, 5)) / 40;
    }
}
