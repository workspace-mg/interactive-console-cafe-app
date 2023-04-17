package common;

import model.AddOn;
import model.Item;

import java.util.Map;

public class Menu {
    public static final Map<Integer, Item> menuItemsMap = Map.of(
            1, new Item(1, "Coffee (Small)", 2.50, "Beverage"),
            2, new Item(2, "Coffee (Medium)", 3.00, "Beverage"),
            3, new Item(3, "Coffee (Large)", 3.50, "Beverage"),
            4, new Item(4, "Bacon roll", 4.50, "Snack"),
            5, new Item(5, "Orange Juice", 3.95, "Drink")
    );
    public static final Map<Integer, AddOn> optionsMap = Map.of(
            1, new AddOn(1, "Extra Milk", 0.30),
            2, new AddOn(2, "Foamed Milk", 0.50),
            3, new AddOn(3, "Special roast coffee", 0.90)
    );
}
