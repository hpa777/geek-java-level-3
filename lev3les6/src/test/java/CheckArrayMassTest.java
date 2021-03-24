import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CheckArrayMassTest {

    private int[] input;

    private  boolean output;

    private static IArraysWork arraysWork;

    @BeforeClass
    public static void beforeClass() throws Exception {
        arraysWork = new ArraysWork();
    }

    public CheckArrayMassTest(int[] input, boolean output) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 1, 1, 4, 4, 1, 4, 1}, true},
                {new int[]{1, 1, 1, 1, 1, 1}, false},
                {new int[]{4, 4, 4, 4}, false},
                {new int[]{1, 4, 4, 1, 1, 4, 3}, false}
        });
    }

    @Test
    public void checkArrayTest() {
        Assert.assertTrue((boolean) output == arraysWork.checkArray(input));
    }
}
