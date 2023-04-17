package controller;

import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CafeService;
import service.DiscountService;
import service.MenuService;
import service.PriceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTestShould {

    private CafeService cafeService;
    private MenuService orderService;
    private DiscountService discountService;
    private PriceService pricer;

    private OrderController orderController;

    @BeforeEach
    void setUp() {
        cafeService = mock(CafeService.class);
        orderService = mock(MenuService.class);
        discountService = mock(DiscountService.class);
        pricer = mock(PriceService.class);
        orderController = new OrderController(cafeService, orderService, discountService, pricer);
    }

    @Test
    void testCreateOrder() {
        Order expected = new Order("TestOrder", new ArrayList<>());
        when(orderService.gatherCustomerInputs()).thenReturn(expected);

        Order actual = orderController.createOrder();

        assertEquals(expected, actual);
        verify(orderService, times(1)).gatherCustomerInputs();
    }

    @Test
    void testCalculateOrderPrice() {
        List<Order> coffeeShopOverAllOrders = new ArrayList<>();
        Order currentOrder = new Order("TestOrder", new ArrayList<>());
        boolean stampCollectionDiscount = true;
        boolean comboDiscount = false;

        Optional<Order> customerOverAllOrders = Optional.empty();
        when(pricer.calculateOrderPrice(currentOrder, stampCollectionDiscount, comboDiscount, 0)).thenReturn(10.0);

        double actual = orderController.calculateOrderPrice(coffeeShopOverAllOrders, currentOrder, stampCollectionDiscount, comboDiscount);

        assertEquals(10.0, actual);
        verify(pricer, times(1)).calculateOrderPrice(currentOrder, stampCollectionDiscount, comboDiscount, 0);
    }

    @Test
    void testUpdateCafeOverAllOrderList() {
        List<Order> coffeeShopOverAllOrders = new ArrayList<>();
        Order currentOrder = new Order("TestOrder", new ArrayList<>());

        when(cafeService.updateCafeOverAllOrderList(coffeeShopOverAllOrders, currentOrder)).thenReturn(List.of(currentOrder));

        List<Order> actual = orderController.updateCafeOverAllOrderList(coffeeShopOverAllOrders, currentOrder);

        assertEquals(List.of(currentOrder), actual);
        verify(cafeService, times(1)).updateCafeOverAllOrderList(coffeeShopOverAllOrders, currentOrder);
    }

}