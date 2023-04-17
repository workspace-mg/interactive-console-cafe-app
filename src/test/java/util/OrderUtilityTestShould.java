package util;

import model.AddOn;
import model.Item;
import model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderUtilityTestShould {

    @ParameterizedTest
    @MethodSource("provideUserInputAndAcceptedValues")
    @DisplayName("Accept input value from user only when it is in accepted values list")
    public void returnValueFromUserOnlyWhenItIsInAcceptedValuesList(Map<String, List<String>> userInputAndAcceptedValues) {
        String mockInput = mockUserInput(userInputAndAcceptedValues.keySet().iterator().next());
        assertEquals(mockInput, OrderUtility.gatherUserInput("Enter mockInput:", userInputAndAcceptedValues.get(mockInput)));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUserInputAndAcceptedValues")
    @DisplayName("Result In Exception when user input is not in accepted values list")
    public void resultInExceptionFlowInformingUserAboutInvalidInput(Map<String, List<String>> invalidInputAndAcceptedValues) {
        String mockInput = mockUserInput(invalidInputAndAcceptedValues.keySet().iterator().next());
        assertThrows(Exception.class, () -> OrderUtility.gatherUserInput("Enter mockInput:", invalidInputAndAcceptedValues.get(mockInput)));
    }

    @ParameterizedTest
    @MethodSource("provideUserInputAndAcceptedRegEx")
    @DisplayName("Accept input value from user only when it matches the regex")
    public void returnValueFromUserOnlyWhenRegExIsMet(Map<String, String> userInputAndAcceptedValues) {
        String mockInput = mockUserInput(userInputAndAcceptedValues.keySet().iterator().next());
        assertEquals(mockInput, OrderUtility.gatherUserInputForNameOrQuantity("Enter mockInput:", userInputAndAcceptedValues.get(mockInput)));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidUserInputAndRegEx")
    @DisplayName("Result In Exception when user input does not match the regex")
    public void resultInExceptionFlowInformingUserAboutInvalidInputWhenRegExIsNotMet(Map<String, List<String>> invalidInputAndAcceptedValues) {
        String mockInput = mockUserInput(invalidInputAndAcceptedValues.keySet().iterator().next());
        assertThrows(Exception.class, () -> OrderUtility.gatherUserInput("Enter mockInput:", invalidInputAndAcceptedValues.get(mockInput)));
    }

    @Test
    @DisplayName("Update AddOn options quantity to match the item quantity")
    public void updateAddOnQuantityInLineWithItemQuantity() {
        Item item = new Item(1, "Coffee", 2, "Beverage");
        item.setQuantity(2);
        item.getAddOns().add(new AddOn(1, "Milk", 1));
        item.getAddOns().add(new AddOn(2, "Sugar", 1));
        OrderUtility.updateAddOnQuantity(item);
        item.getAddOns().forEach(addOn -> assertEquals(item.getQuantity(), addOn.getQuantity()));
    }

    @Test
    @DisplayName("Return Zero number of free coffees when sum of all beverage items is less than Five")
    public void ReturnsNumberOfFreeCoffeesAsZeroWhenSumOfAllBeverageItemsIsLessThanFour() {
        Order order = new Order();
        order.getItems().add(new Item(1, "Coffee", 2, "Beverage"));
        order.getItems().add(new Item(2, "Tea", 2, "Beverage"));
        order.getItems().add(new Item(3, "Sandwich", 5, "Food"));
        order.getItems().add(new Item(4, "Cake", 5, "Food"));
        order.getItems().forEach(item -> item.setQuantity(2));
        assertEquals(0, OrderUtility.calculateFreeCoffees(order));
    }

    @ParameterizedTest
    @MethodSource("provideBeverageQuantityAndExpectedFreeCoffees")
    @DisplayName("Return every fifth beverage drink as free")
    public void ReturnsNumberOfFreeCoffeesAsOneWhenSumOfAllBeverageItemsIsGreaterThanFour(int beverageQuantity, int expectedFreeCoffees) {
        Order order = new Order();
        order.getItems().add(new Item(1, "Coffee", 2, "Beverage"));
        order.getItems().forEach(item -> {
            item.setQuantity(beverageQuantity);
        });
        assertEquals(expectedFreeCoffees, OrderUtility.calculateFreeCoffees(order));
    }

    private static String mockUserInput(String userInputAndAcceptedValues) {
        InputStream in = new ByteArrayInputStream(userInputAndAcceptedValues.getBytes());
        System.setIn(in);
        return userInputAndAcceptedValues;
    }

    public static Stream<Arguments> provideUserInputAndAcceptedValues() {
        return Stream.of(
                Arguments.of(Map.of("1", List.of("1", "2", "3"))),
                Arguments.of(Map.of("y", List.of("Y", "y"))),
                Arguments.of(Map.of("n", List.of("N", "n")))
        );
    }

    public static Stream<Arguments> provideUserInputAndAcceptedRegEx() {
        return Stream.of(
                Arguments.of(Map.of("a", "^[a-zA-Z]+$")),
                Arguments.of(Map.of("1", "^[0-9]+$"))
        );
    }

    public static Stream<Arguments> provideInvalidUserInputAndAcceptedValues() {
        return Stream.of(
                Arguments.of(Map.of("123", List.of("1", "2", "3"))),
                Arguments.of(Map.of("Y", List.of("N", "n")))
        );
    }

    public static Stream<Arguments> provideInvalidUserInputAndRegEx() {
        return Stream.of(
                Arguments.of(Map.of("1", "^[a-zA-Z]+$")),
                Arguments.of(Map.of("a", "^[0-9]+$"))
        );
    }

    public static Stream<Arguments> provideBeverageQuantityAndExpectedFreeCoffees() {
        return Stream.of(
                Arguments.of(5, 1),
                Arguments.of(10, 2),
                Arguments.of(15, 3),
                Arguments.of(20, 4),
                Arguments.of(25, 5)
        );
    }
}
