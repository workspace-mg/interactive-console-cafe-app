package util;

import model.AddOn;

import java.util.Comparator;

public class PriceComparator implements Comparator<AddOn> {

    @Override
    public int compare(AddOn obj1, AddOn obj2) {
        return Double.compare(obj2.getPrice(), obj1.getPrice());
    }
}

