package lesson5;

import java.util.Arrays;
import java.util.concurrent.*;

public class MainClass {

    public static final int CARS_COUNT = 4;

    public static final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT + 1);

    public static final Semaphore semaphore = new Semaphore(CARS_COUNT / 2);

    public static final ArrayBlockingQueue<String> finish = new ArrayBlockingQueue<>(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        ExecutorService executor = Executors.newFixedThreadPool(CARS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            executor.execute(cars[i]);
        }
        try {
            startBarrier.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            System.out.println(finish.take() + " - WIN");
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }



    }
}
