package entities;

public class Shop {

    private Integer amountProduct;

    public Shop(Integer amountProduct) {
        this.amountProduct = amountProduct;
    }

    public synchronized boolean amountProductIsEmpty() {
        return amountProduct == 0;
    }

    public synchronized Integer buyProduct(int amountBuy) {
        if (amountBuy > amountProduct) {
            amountBuy = amountProduct;
            amountProduct = 0;
        } else {
            amountProduct -= amountBuy;
        }
        return amountBuy;
    }
}
