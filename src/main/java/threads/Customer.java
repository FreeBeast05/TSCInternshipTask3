package threads;

import action.Main;
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
    private final CyclicBarrier BARRIER = Main.BARRIER;

    public Customer(int id, Shop shop) {
        this.id = id;
        this.shop = shop;
    }


    @Override
    public void run() {
        while (shop.getAmountProduct().get() != 0) {
            try {
                int amountBuy = rand.nextInt(11);
                Integer amount = shop.buyProduct(amountBuy);
                sumBuy += amount;
                countPurchase++;
                BARRIER.await();
            } catch (BrokenBarrierException e) {
//                System.out.print("");
            } catch (InterruptedException e){
                System.out.println("Возникла ошибка работы с потоком");
            }
        }
        if (shop.getAmountProduct().get() == 0)
            BARRIER.reset();
        System.out.println("Customer" + id + ": countPurchase - " + countPurchase + ", sumPrice= " + sumBuy);
    }
}
