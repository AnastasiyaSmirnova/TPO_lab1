import org.junit.Assert;
import org.junit.Test;

import static java.lang.Math.*;

public class FunctionTest {

    @Test
    public void arccos_Minus1() throws IncorrectInputArgument {
        double actual = Function.arccos(-1);
        Assert.assertEquals(PI, actual, pow(10, -6));
    }

    @Test
    public void arccos_5PI_6() throws IncorrectInputArgument {
        double actual = Function.arccos(-0.5 * pow(3, 0.5));
        double expected = 5 * PI / 6.;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_3PI_4() throws IncorrectInputArgument {
        double actual = Function.arccos(-0.5 * pow(2, 0.5));
        double expected = 3 * PI / 4.;

        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_2PI_3() throws IncorrectInputArgument {
        double actual = Function.arccos(-0.5);
        double expected = 2 * PI / 3.;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_0() throws IncorrectInputArgument {
        double actual = Function.arccos(0);
        double expected = PI / 2.;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_PI_3() throws IncorrectInputArgument {
        double actual = Function.arccos(0.5);
        double expected = PI / 3.;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_PI_4() throws IncorrectInputArgument {
        double actual = Function.arccos(0.5 * pow(2, 0.5));
        double expected = PI / 4.;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_PI_6() throws IncorrectInputArgument {
        double actual = Function.arccos(0.5 * pow(3, 0.5));
        double expected = PI / 6.;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }

    @Test
    public void arccos_Plus1() throws IncorrectInputArgument {
        double actual = Function.arccos(1);
        double expected = 0;
        Assert.assertEquals(expected, actual, pow(10, -6));
    }


    @Test(expected = IncorrectInputArgument.class)
    public void arccos_ExceptionBigger1() throws IncorrectInputArgument {
        Function.arccos(1 + pow(10, -6));
    }

    @Test(expected = IncorrectInputArgument.class)
    public void arccos_ExceptonSmallerMinus1() throws IncorrectInputArgument {
        Function.arccos(-1 - pow(10, -6));
    }

    @Test(expected = IncorrectInputArgument.class)
    public void arccos_NegaviteInfiity() throws IncorrectInputArgument {
        Function.arccos(Double.NEGATIVE_INFINITY);
    }

    @Test(expected = IncorrectInputArgument.class)
    public void arccos_PositiveInfinity() throws IncorrectInputArgument {
        Function.arccos(Double.POSITIVE_INFINITY);
    }

    @Test(expected = IncorrectInputArgument.class)
    public void arccos_NAN() throws IncorrectInputArgument {
        Function.arccos(Double.NaN);
    }

}