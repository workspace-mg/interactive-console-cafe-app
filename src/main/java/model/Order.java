package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String name;

    private int stampCard;

    public Order() {

    }

    public <E> Order(String testOrder, ArrayList<E> es) {
    }

    public int getStampCard() {
        return stampCard;
    }

    public void setStampCard(int stampCard) {
        this.stampCard = stampCard;
    }

    private final List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items.addAll(items);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order { Name: " + name + ", Items: " + items;
    }
}
