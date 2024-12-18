package ma.emsi.Controller;

import ma.emsi.Model.Order;
import ma.emsi.Service.OrderService;
import ma.emsi.dto.Order.OrderRespo;
import ma.emsi.dto.Order.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public OrderRespo placeOrder(@RequestBody PlaceOrderRequest request) {
        Order order = orderService.placeOrder(request.getCartId(), request.getAddressId(), request.getUserId());

        // Create and populate OrderResponse object
        OrderRespo response = new OrderRespo();
        response.setOrderId(order.getId());
        response.setStatus("Success");  // or any other status logic you have
        response.setMessage("Order placed successfully");

        return response;
    }

}
