package util;

import model.Item;
import model.Order;

import java.util.List;
import java.util.Scanner;

import static common.Menu.menuItemsMap;
import static common.Menu.optionsMap;

public class OrderUtility {

    private static final int DISCOUNT_ON_NUMBER_OF_COFFEES = 4;

    private final static String INVALID_INPUT_MESSAGE = "Invalid input, please enter valid input.....";

    public static void displayMenuItems() {
        menuItemsMap.values().forEach(System.out::println);
    }

    public static String gatherUserInput(String text, List<String> validInputs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String userInput;
        do {
            userInput = scanner.nextLine().trim();
            if (!validInputs.contains(userInput)) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        } while(!validInputs.contains(userInput));
        return userInput;
    }

    public static String gatherUserInputForNameOrQuantity(String prompt, String regex) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String userInput;
        do {
            userInput = scanner.nextLine().trim();
            System.out.println(INVALID_INPUT_MESSAGE);
        } while (!userInput.matches(regex));
        return userInput;
    }

    public static String gatherUserAddOnChoice() {
        optionsMap.values().forEach(System.out::println);
        return gatherUserInput("Enter add-on number or 0 to skip", List.of("0", "1", "2", "3"));
    }

    public static int calculateFreeCoffees(Order order) {
        int numberOfBeverages = order.getItems().stream().filter(item ->
                "Beverage".equals(item.getType())).mapToInt(Item::getQuantity).sum();
        return numberOfBeverages / (DISCOUNT_ON_NUMBER_OF_COFFEES + 1);
    }

    public static void updateAddOnQuantity(Item item) {
        item.getAddOns().forEach(addOn -> addOn.setQuantity(item.getQuantity()));
    }

}
