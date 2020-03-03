package ua.electron.service.lowerLevel.shoppingCartActions;

import java.util.Objects;

public class PriceCounter {

    private int productIdFromCookie;
    private int productQuantityFromCookie;
    private int productPrice;

    public PriceCounter(int productIdFromCookie, int productQuantityFromCookie, int productPrice) {
        this.productIdFromCookie = productIdFromCookie;
        this.productQuantityFromCookie = productQuantityFromCookie;
        this.productPrice = productPrice;
    }

    public int getProductIdFromCookie() {
        return productIdFromCookie;
    }

    public void setProductIdFromCookie(int productIdFromCookie) {
        this.productIdFromCookie = productIdFromCookie;
    }

    public int getProductQuantityFromCookie() {
        return productQuantityFromCookie;
    }

    public void setProductQuantityFromCookie(int productQuantityFromCookie) {
        this.productQuantityFromCookie = productQuantityFromCookie;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceCounter that = (PriceCounter) o;
        return productIdFromCookie == that.productIdFromCookie &&
                productQuantityFromCookie == that.productQuantityFromCookie &&
                Double.compare(that.productPrice, productPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productIdFromCookie, productQuantityFromCookie, productPrice);
    }

    @Override
    public String toString() {
        return "PriceCounter{" +
                "productIdFromCookie=" + productIdFromCookie +
                ", productQuantityFromCookie=" + productQuantityFromCookie +
                ", productPrice=" + productPrice +
                '}';
    }
}
