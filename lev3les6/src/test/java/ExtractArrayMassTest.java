import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExtractArrayMassTest {

    private int[] input;

    private Object output;
    
    private static IArraysWork arraysWork;

    public ExtractArrayMassTest(int[] input, Object output) {
        this.input = input;
        this.output = output;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        arraysWork = new ArraysWork();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[] {1, 7 }},
                {new int[] {1, 2, 4, 4}, new int[] {}},
                {new int[] {4, 4, 2, 3, 4, 1, 7, 5, 1}, new int[] {1, 7, 5, 1}},
                {new int[] {7, 5, 1}, RuntimeException.class},
                {new int[] {}, RuntimeException.class}
        });
    }

    @Test
    public void extractArrayTest() {
        int[] result = null;
        try {
            result = arraysWork.extractArray(input);
        } catch (Exception e) {
            Assert.assertTrue(output == e.getClass());
            return;
        }
        Assert.assertArrayEquals((int[]) output, result);

    }


}
