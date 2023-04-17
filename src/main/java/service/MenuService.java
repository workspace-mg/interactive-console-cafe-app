package service;

import model.AddOn;
import model.Item;
import model.Order;
import util.OrderUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static common.Menu.menuItemsMap;
import static common.Menu.optionsMap;
import static util.OrderUtility.*;

public class MenuService {
    private final List<String> COFFEE_ITEMS = Arrays.asList("1", "2", "3");

    public Order gatherCustomerInputs() {
        Order order = new Order();
        List<Item> items = new ArrayList<>();
        System.out.println("Welcome to the coffee corner!");
        String name = gatherUserInputForNameOrQuantity("What is your name?", "^[a-zA-Z]+$");
        order.setName(name);
        System.out.println("Hello " + name + "! Here is our menu:");
        Stream.iterate("Y", "Y"::equalsIgnoreCase, repeatedOrder -> gatherUserInput("Would you like to order something else? (Y/N/y/n)", List.of("Y", "N", "y", "n")))
                .forEach(repeatedOrder -> {
                    displayMenuItems();
                    items.add(gatherItems(Integer.parseInt(
                            gatherUserInput("What would you like to order? Enter number of the item", List.of("1", "2", "3", "4", "5")))));
                });
        order.setItems(items);
        return order;
    }

    private Item gatherItems(int orderNumber) {
        Item item = menuItemsMap.get(orderNumber);
        gatherAddOns(item, String.valueOf(orderNumber));
        item.setQuantity(Integer.parseInt(gatherUserInputForNameOrQuantity("Item quantity?", "^[0-9]+$")));
        updateAddOnQuantity(item);
        return item;
    }

    private void gatherAddOns(Item item, String orderNumber) {
        List<AddOn> options = new ArrayList<>();
        if (COFFEE_ITEMS.contains(orderNumber)) {
            System.out.println("We have following add-ons:");
            Stream.generate(OrderUtility::gatherUserAddOnChoice)
                    .takeWhile(optionNumber -> !"0".equals(optionNumber))
                    .mapToInt(Integer::parseInt)
                    .mapToObj(optionsMap::get)
                    .peek(options::add)
                    .anyMatch(option -> "N".equalsIgnoreCase(gatherUserInput("More add-ons? (Y/N/y/n)", List.of("Y", "N", "y", "n"))));
            item.setAddOns(options);
        }
    }



}
