package service;

import model.Order;
import model.Product;
import model.Item;

import java.util.List;

public class CafeService {

    public List<Order> updateCafeOverAllOrderList(List<Order> overallOrders, Order currentOrder) {
        overallOrders.stream()
                .filter(order -> order.getName().equalsIgnoreCase(currentOrder.getName()))
                .findFirst().ifPresentOrElse(
                        order -> {
                            updateCustomerVisit(order, currentOrder);
                            order.getItems().addAll(currentOrder.getItems());
                        },
                        () -> {
                            updateCustomerVisit(currentOrder, currentOrder);
                            overallOrders.add(currentOrder);
                        }
                );

        return overallOrders;
    }

    private void updateCustomerVisit(Order existingOrder, Order newOrder) {
        int beverageQuantity = newOrder.getItems().stream()
                .filter(item -> "Beverage".equals(item.getType()))
                .mapToInt(Item::getQuantity)
                .sum();
        existingOrder.setStampCard(existingOrder.getStampCard() + beverageQuantity);
    }

    public void generateReceipt(List<Product> products) {
        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%35s %10s %20s %10s %10s", "PRODUCT", "QUANTITY", "PRICE_WITH_OUT_ADDON", "ADDON_PRICE", "PRICE_AFTER_DISCOUNT");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for(Product product: products){
            System.out.format("%35s %10s %20s %10s %10s",
                    product.getName(), 1, product.getItemPrice(), product.getAddOnPrice());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }
}
