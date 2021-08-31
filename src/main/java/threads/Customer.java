package threads;

import entities.Shop;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Customer implements Runnable {

    private final int id;
    private int countPurchase;
    private int sumBuy;
    private final Shop shop;
    private final Random rand = new Random();
    private final CyclicBarrier barrier;
    private final int randRangeBuy = 10;


    public Customer(int id, Shop shop, CyclicBarrier barrier) {
        this.id = id;
        this.shop = shop;
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try {
            while (!shop.amountProductIsEmpty()) {
                int amountBuy = rand.nextInt(randRangeBuy) + 1;
                int amount = shop.buyProduct(amountBuy);
                if (amount > 0) {
                    countPurchase++;
                    sumBuy += amount;
                }
                barrier.await();
            }
            if (barrier.getNumberWaiting() > 0) {
                barrier.await();
            }
            System.out.println("Customer" + id + ": countPurchase - " + countPurchase + ", sumPrice= " + sumBuy);
        } catch (InterruptedException | BrokenBarrierException e) {
            System.out.println("Возникла ошибка работы с потоком");
        }
    }
}
