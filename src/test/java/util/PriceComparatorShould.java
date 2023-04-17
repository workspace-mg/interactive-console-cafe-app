package util;
import model.AddOn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceComparatorShould {

    PriceComparator priceComparator = new PriceComparator();

    @Test
    @DisplayName("Return Zero When Two Objects With Same Price Are Compared")
    public void ReturnZeroWhenTwoObjectsWithSamePriceAreCompared() {
        AddOn firstAddOn = getAddOn(1, "Milk", 1.0);
        AddOn secondAddOnWithSamePriceAsFirst = getAddOn(2, "Sugar", 1.0);
        assertEquals(0, priceComparator.compare(firstAddOn, secondAddOnWithSamePriceAsFirst));
    }
    @Test
    @DisplayName("Return Negative When Higher Is Compared To Lower And Positive When Lower Is Compared To Higher")
    public void ReturnNegativeWhenHigherIsComparedToLowerAndPositiveWhenLowerIsComparedToHigher() {
        AddOn addOnWithHigherPrice = getAddOn(1, "Milk", 2.0);
        AddOn addOnWithLowerPrice = getAddOn(2, "Sugar", 1.5);
        assertEquals(-1, priceComparator.compare(addOnWithHigherPrice, addOnWithLowerPrice));
        assertEquals(1, priceComparator.compare(addOnWithLowerPrice, addOnWithHigherPrice));
    }

    private AddOn getAddOn(int id, String name, double price) {
        return new AddOn(id, name, price);
    }
}