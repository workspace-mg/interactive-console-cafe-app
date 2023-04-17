package util;

import model.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product obj1, Product obj2) {
        return Double.compare(obj2.getItemPrice(), obj1.getItemPrice());
    }
}
