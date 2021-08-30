package action;

import entities.Shop;
import threads.Customer;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        int quantityGoods = 1000;
        try {
            int numberThreads = Integer.parseInt(args[0]);
            CyclicBarrier barrier = new CyclicBarrier(numberThreads);
            Shop shop = new Shop(quantityGoods);
            for (int i = 0; i < numberThreads; i++) {
                Thread customer = new Thread(new Customer(i, shop, barrier));
                customer.start();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Не введено количество покупателей");
        }

    }
}
