package model;

import java.util.ArrayList;
import java.util.List;

public class Item extends AddOn {

    public Item(int id, String name, double price, String type) {
        super(id, name, price);
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }

    private List<AddOn> addOns;

    public List<AddOn> getAddOns() {
        return addOns != null ? addOns : new ArrayList<>();
    }

    public void setAddOns(List<AddOn> addOns) {
        this.addOns = addOns;
    }

    public String toString() {
        //String itemWithOutOptions = this.getId() + ". " + this.getName() + " - CHF " + this.getPrice();
        //String addOns = this.addOns == null || this.addOns.size() < 1 ? "" : " with add-on/s : " + this.addOns;
        //return itemWithOutOptions + addOns;
        return this.getId() + ". " + this.getName() + " - CHF " + this.getPrice();
    }
}
