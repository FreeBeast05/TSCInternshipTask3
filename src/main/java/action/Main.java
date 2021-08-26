package action;

import entities.Shop;
import threads.Customer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static CyclicBarrier BARRIER;
    public static void main(String[] args) {
        try {
            int numberThreads = Integer.parseInt(args[0]);
            BARRIER = new CyclicBarrier(numberThreads);
            Shop shop = Shop.init(1000);
            ExecutorService executor = Executors.newFixedThreadPool(numberThreads);
            for (int i = 0; i < numberThreads; i++) {
                executor.submit(new Customer(i, shop));
            }
            executor.shutdown();
        } catch (IndexOutOfBoundsException e){
            System.out.println("Не введено количество покупателей");
        }

    }
}
