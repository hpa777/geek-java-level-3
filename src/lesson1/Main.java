package lesson1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] arr = { "One", "Two", "Three"};
        String[] res = swap(0, 2, arr);
        System.out.println(Arrays.toString(res));
        ArrayList<String> s = convertToArrayList(arr);

        Box<Apple> box1 = new Box<>();
        box1.addFruit(new Apple(0.5f));
        box1.addFruit(new Apple(0.67f));
        box1.addFruit(new Apple(0.52f));

        Box<Orange> box2 = new Box<>();
        box2.addFruit(new Orange(0.5f));
        box2.addFruit(new Orange(0.67f));
        box2.addFruit(new Orange(0.52f));

        System.out.println(box1.compare(box2));

        Box<Apple> box3 = new Box<>();
        box3.addFruit(new Apple(0.55f));
        box3.addFruit(new Apple(0.77f));
        box3.addFruit(new Apple(0.82f));

        System.out.println(box1.getWeight());
        System.out.println(box3.getWeight());

        box1.pour(box3);

        System.out.println(box1.getWeight());
        System.out.println(box3.getWeight());
    }

    private static <T> T[] swap(int i, int j, T... arr) {
        if (i >= arr.length || j >= arr.length || i < 0 || j < 0 || i == j ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T obj = arr[i];
        arr[i] = arr[j];
        arr[j] = obj;
        return arr;
    }

    private static <T> ArrayList<T> convertToArrayList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

}
