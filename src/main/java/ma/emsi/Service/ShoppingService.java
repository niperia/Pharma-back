package ma.emsi.Service;



import ma.emsi.Model.*;
import ma.emsi.Model.Enum.PaymentMethod;
import ma.emsi.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    // Create an order from a cart
    public Order createOrder(Integer cartId, String address, PaymentMethod paymentMethod) {
        Optional<Cart> cartOptional = cartService.getCartById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Double totalAmount = cartService.getCartTotalPrice(cart);
            Order order = new Order(cart, address, paymentMethod, totalAmount);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }
}
