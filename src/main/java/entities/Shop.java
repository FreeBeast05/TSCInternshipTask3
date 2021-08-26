package entities;

import java.util.concurrent.atomic.AtomicInteger;

public class Shop {
    private static Shop shop;
    private final AtomicInteger amountProduct;

    private Shop(AtomicInteger amountProduct) {
        this.amountProduct = amountProduct;
    }

    public AtomicInteger getAmountProduct() {
        return amountProduct;
    }


    public static Shop init(Integer amountProduct){
        if (shop == null){
            shop = new Shop(new AtomicInteger(amountProduct));
        }
        return shop;
    }

    public synchronized Integer buyProduct(Integer amountBuy){
        if (amountBuy > amountProduct.get()){
            amountBuy = amountProduct.get();
            amountProduct.set(0);
        }
        else {
            amountProduct.addAndGet(-amountBuy);
        }
        return amountBuy;
    }
}
