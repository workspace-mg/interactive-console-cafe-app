package service;

import model.Order;
import model.Product;
import model.Item;
import util.AddOnComparator;
import util.ProductPriceComparator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static util.OrderUtility.calculateFreeCoffees;

public class DiscountService {

    private final List<String> ITEM_TYPE_LIST_VALID_FOR_DISCOUNT = Arrays.asList("Beverage", "Snack");

    private final int DISCOUNT_ON_NUMBER_OF_COFFEES = 4;

    public boolean isOrderQualifiedForComboDiscount(Order order) {
        return order.getItems().stream()
                .map(Item::getType).collect(Collectors.toSet()).containsAll(ITEM_TYPE_LIST_VALID_FOR_DISCOUNT);
    }

    public boolean isOrderQualifiedForStampCardDiscount(List<Order> overallOrders, String customerName) {
        return overallOrders.stream()
                .filter(order -> order.getName().equalsIgnoreCase(customerName))
                .findFirst()
                .map(order -> order.getStampCard() > DISCOUNT_ON_NUMBER_OF_COFFEES)
                .orElse(false);
    }

    public List<Order> updateStampCard(List<Order> cafeOverAllOrders, String customerName) {
        cafeOverAllOrders.stream()
                .filter(order -> order.getName().equalsIgnoreCase(customerName))
                .findFirst()
                .ifPresent(order -> {
                    int freeCoffees = calculateFreeCoffees(order);
                    int availedDiscount = DISCOUNT_ON_NUMBER_OF_COFFEES * freeCoffees;
                    order.setStampCard(order.getStampCard() - availedDiscount - freeCoffees);
                });
        return cafeOverAllOrders;
    }

    public boolean isQualifiedForSnackAndBeverageComboDiscount(List<Product> products) {
        return products.stream().map(Product::getType).collect(Collectors.toSet()).containsAll(ITEM_TYPE_LIST_VALID_FOR_DISCOUNT);
    }

    public int getNumberOfFreeCoffee(List<Product> products) {
        int numberOfBeverages = Math.toIntExact(products.stream().filter(product -> product.getType().equalsIgnoreCase("Beverage")).count());
        return numberOfBeverages / 5;
    }

    public List<Product> applyDiscounts(List<Product> products) {
        int numberOfFreeCoffee = getNumberOfFreeCoffee(products);
        boolean isQualifiedForSnackAndBeverageComboDiscount = isQualifiedForSnackAndBeverageComboDiscount(products);
        products.stream().filter(product -> product.getType().equalsIgnoreCase("Beverage")).sorted(new ProductPriceComparator())
                .limit(numberOfFreeCoffee).forEach(product -> product.setItemPriceAfterDiscount(0));
        if (isQualifiedForSnackAndBeverageComboDiscount && numberOfFreeCoffee == 0) {
            products.stream().filter(product -> product.getType().equalsIgnoreCase("Beverage")).sorted(new AddOnComparator())
                    .limit(1).forEach(product -> product.setAddOnPriceAfterDiscount(0));
        }
        return products;
    }
}
