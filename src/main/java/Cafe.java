import controller.OrderController;
import model.Order;
import service.CafeService;
import service.DiscountService;
import service.MenuService;
import service.PriceService;

import java.util.ArrayList;
import java.util.List;

import static util.OrderUtility.gatherUserInput;

public class Cafe {


    private static List<Order> coffeeShopOverAllOrders = new ArrayList<>();

    private static final DiscountService discountService = new DiscountService();
    private static final CafeService cafeService = new CafeService();
    private static final PriceService pricer = new PriceService();
    private static final MenuService menuService = new MenuService();
    static final OrderController orderController = new OrderController(cafeService, menuService, discountService, pricer);


    public static void main(String[] args) {
        String takeOrder = "Y";
        while ("Y".equalsIgnoreCase(takeOrder)) {
            Order currentOrder = orderController.createOrder();
            System.out.println("Your order details are:");
            orderController.printOrderDetails(currentOrder);
            System.out.println("");
            gatherUserInput("Please confirm your details? (Y/y)", List.of("Y", "y"));
            coffeeShopOverAllOrders = orderController.updateCafeOverAllOrderList(coffeeShopOverAllOrders, currentOrder);
            boolean stampCollectionDiscount = discountService.isOrderQualifiedForStampCardDiscount(coffeeShopOverAllOrders, currentOrder.getName());
            boolean comboDiscount = discountService.isOrderQualifiedForComboDiscount(currentOrder);
            double orderPrice = orderController.calculateOrderPrice(coffeeShopOverAllOrders, currentOrder, stampCollectionDiscount, comboDiscount);
            coffeeShopOverAllOrders = orderController.updateStampCard(coffeeShopOverAllOrders, currentOrder.getName());
            System.out.println("Your order is placed and here is your receipt:");
            orderController.printOrderDetails(currentOrder);
            System.out.println("");
            System.out.println("*******************************");
            System.out.println("Amount to be paid after discounts if any: " + orderPrice);
            if (stampCollectionDiscount) {
                System.out.println("Stamp card discount applied!! You received 1 quantity of highest priced beverage for free.");
            } else if (comboDiscount) {
                System.out.println("Snack and Beverage combo discount applied!! You received 1 quantity of highest priced add-on for free.");
            }
            System.out.println("*******************************");
            coffeeShopOverAllOrders.stream()
                    .filter(order -> order.getName().equalsIgnoreCase(currentOrder.getName()))
                    .findFirst().ifPresent(customer -> System.out.println("Your balance stamps are: " + customer.getStampCard()));
            System.out.println("Thank you for visiting us!");
            takeOrder = gatherUserInput("Do you want to take another order? (Y/N/y/n)", List.of("Y", "N", "y", "n"));
        }
        System.out.println("We are closing now. Rerun the program to start a new day.");
    }
}