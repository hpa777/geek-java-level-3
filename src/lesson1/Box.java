package lesson1;

import java.util.ArrayList;

public class Box<T extends Fruit>  {

    private final ArrayList<T> list = new ArrayList<>();

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public float getWeight() {
        float weight = 0.0f;
        for (T item : list) {
            weight+=item.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> o) {
        float w1 = this.getWeight();
        float w2 = o.getWeight();
        return Math.abs(w1 - w2) < 0.00001f;
    }

    public void pour(Box<T> o) {
        if (o == this) {
            return;
        }
        this.list.addAll(o.list);
        o.list.clear();
    }
}
