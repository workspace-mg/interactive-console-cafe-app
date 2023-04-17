package service;

import model.AddOn;
import model.Item;
import model.Order;
import model.Product;
import util.PriceComparator;

import java.util.List;
import java.util.stream.Collectors;

public class PriceService {

    private static final int DISCOUNT_ON_NUMBER_OF_COFFEES = 4;

    public double calculateOrderPrice(Order order, boolean stampCardDiscount, boolean comboDiscount, int collectedStamps) {
        List<AddOn> addOns = order.getItems().stream().flatMap(item -> item.getAddOns().stream()).sorted(new PriceComparator()).collect(Collectors.toList());
        List<Item> items = order.getItems().stream().sorted(new PriceComparator()).collect(Collectors.toList());
        int freeCoffee = collectedStamps / (DISCOUNT_ON_NUMBER_OF_COFFEES + 1);
        return addOnListPriceAfterDiscount(addOns, comboDiscount) + itemListPriceAfterDiscount(items, stampCardDiscount, freeCoffee);
    }

    public double addOnListPriceAfterDiscount(List<AddOn> addOns, boolean comboDiscount) {
        double discountPrice = 0;
        boolean discountApplied = !comboDiscount;
        for (AddOn addOn : addOns) {
            int quantity = discountApplied ? addOn.getQuantity() : addOn.getQuantity() - 1;
            discountPrice += addOn.getPrice() * quantity;
            discountApplied = true;
        }
        return discountPrice;
    }

    public double itemListPriceAfterDiscount(List<Item> items, boolean applyDiscount, int discountQuantity) {
        double discountedPrice = 0;

        for (Item item : items) {
            int quantity = applyDiscount && discountQuantity != 0 && item.getType().equals("Beverage")
                    ? Math.max(item.getQuantity() - discountQuantity, 0) : item.getQuantity();

            discountedPrice += item.getPrice() * quantity;

            if (discountQuantity != 0 && item.getType().equals("Beverage")) {
                discountQuantity = Math.max(discountQuantity - item.getQuantity(), 0);
            }
        }

        return discountedPrice;
    }

    public double calculateTotalPrice(List<Product> productList) {
        return productList.stream()
                .map(product -> product.getAddOnPriceAfterDiscount() + product.getItemPriceAfterDiscount())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

}
