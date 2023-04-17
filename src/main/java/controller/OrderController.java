package controller;

import model.Order;
import service.CafeService;
import service.DiscountService;
import service.MenuService;
import service.PriceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OrderController {
    private final CafeService cafeService;
    private final MenuService menuService;
    private final DiscountService discountService;
    private final PriceService pricer;

    public OrderController(CafeService cafeService, MenuService menuService, DiscountService discountService, PriceService pricer) {
        this.cafeService = cafeService;
        this.menuService = menuService;
        this.discountService = discountService;
        this.pricer = pricer;
    }

    public Order createOrder() {
        return menuService.gatherCustomerInputs();
    }

    public double calculateOrderPrice(List<Order> coffeeShopOverAllOrders, Order currentOrder, boolean stampCollectionDiscount, boolean comboDiscount) {
        Optional<Order> customerOverAllOrders = coffeeShopOverAllOrders.stream().filter(order -> order.getName().equalsIgnoreCase(currentOrder.getName())).findFirst();
        return pricer.calculateOrderPrice(currentOrder, stampCollectionDiscount, comboDiscount,
                customerOverAllOrders.map(Order::getStampCard).orElse(0));
    }

    public List<Order> updateCafeOverAllOrderList(List<Order> coffeeShopOverAllOrders, Order currentOrder) {
        return cafeService.updateCafeOverAllOrderList(coffeeShopOverAllOrders, currentOrder);
    }

    public List<Order> updateStampCard(List<Order> coffeeShopOverAllOrders, String customerName) {
        return discountService.updateStampCard(coffeeShopOverAllOrders, customerName);
    }

    public void printOrderDetails(Order order) {
        System.out.println("Name: " + order.getName());
        System.out.println("Order time: " + LocalDateTime.now());
        order.getItems().forEach(item -> {
                    System.out.println(
                            "item: " + item.getName() + "; Type- " + item.getType() +
                                    "; Price per Item- " + item.getPrice() + "; Quantity- " + item.getQuantity());
                    item.getAddOns().forEach(addOn -> System.out.print(
                            " :: AddOn- " + addOn.getName() + "; Price per AddOn- " + addOn.getPrice() + "; Quantity- " + addOn.getQuantity()));
                }
        );
    }
}
