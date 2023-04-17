package model;

public class Product {
    private final String name;
    private final double itemPrice;
    private final double addOnPrice;
    private final String type;

    private double itemPriceAfterDiscount;

    private double addOnPriceAfterDiscount;

    public Product(String name, double itemPrice, double addOnPrice, String type) {
        this.name = name;
        this.itemPrice = itemPrice;
        this.addOnPrice = addOnPrice;
        this.type = type;
        this.addOnPriceAfterDiscount = addOnPrice;
        this.itemPriceAfterDiscount = itemPrice;
    }

    public String getName() {
        return name;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getAddOnPrice() {
        return addOnPrice;
    }

    public String getType() {
        return type;
    }

    public double getItemPriceAfterDiscount() {
        return itemPriceAfterDiscount;
    }

    public void setItemPriceAfterDiscount(double itemPriceAfterDiscount) {
        this.itemPriceAfterDiscount = itemPriceAfterDiscount;
    }

    public double getAddOnPriceAfterDiscount() {
        return addOnPriceAfterDiscount;
    }

    public void setAddOnPriceAfterDiscount(double addOnPriceAfterDiscount) {
        this.addOnPriceAfterDiscount = addOnPriceAfterDiscount;
    }
}
