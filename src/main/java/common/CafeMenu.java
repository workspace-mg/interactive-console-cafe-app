package common;

import model.Product;

import java.util.Arrays;
import java.util.List;

public class CafeMenu {
    private static final List<Product> AVAILABLE_PRODUCTS
            = Arrays.asList(
            new Product("Small Coffee", 2.50, 0, "Beverage"),
            new Product("Medium Coffee", 3.00, 0, "Beverage"),
            new Product("Large Coffee", 3.50, 0, "Beverage"),
            new Product("Small Coffee With Extra Milk", 2.50, 0.30, "Beverage"),
            new Product("Small Coffee With Foamed Milk", 2.50, 0.50, "Beverage"),
            new Product("Small Coffee With Special Roast", 2.50, 0.90, "Beverage"),
            new Product("Medium Coffee With Extra Milk", 3.00, 0.30, "Beverage"),
            new Product("Medium Coffee With Foamed Milk", 3.00, 0.50, "Beverage"),
            new Product("Medium Coffee With Special Roast", 3.00, 0.90, "Beverage"),
            new Product("Large Coffee With Extra Milk", 3.50, 0.30, "Beverage"),
            new Product("Large Coffee With Foamed Milk", 3.50, 0.50, "Beverage"),
            new Product("Large Coffee With Special Roast", 3.50, 0.90, "Beverage"),
            new Product("Bacon Roll", 4.50, 0, "Snack"),
            new Product("Orange Juice", 3.95, 0, "Drink")
    );
}
