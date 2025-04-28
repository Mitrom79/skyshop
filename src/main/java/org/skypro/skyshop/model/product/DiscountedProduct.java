package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int defaultPrice;
    private int discount;

    public DiscountedProduct(String name, UUID id, int defaultPrice, int discount) {
        super(name, id);

        if(defaultPrice < 0) throw new IllegalArgumentException("Цена не может быть отрицательной");
        if(discount < 0 || discount > 100) throw new IllegalArgumentException("Скидка не может быть отрицательной или больше 100%");

        this.defaultPrice = defaultPrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return defaultPrice - (defaultPrice * discount / 100);
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
