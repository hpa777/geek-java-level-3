package lesson7;

public class MyTest {

    @Test(priority = 4)
    public static void test1() {
        System.out.println("Test1");
    }
    @Test
    public static void test2() {
        System.out.println("Test2");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test3");
    }

    @BeforeSuite
    public void before() {
        System.out.println("before");
    }
}
