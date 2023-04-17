package model;

public class AddOn {
    private final int id;
    private final String name;
    private final double price;
    private int quantity;

    public AddOn(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.name + " - CHF " + this.price;
    }
}
