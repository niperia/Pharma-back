package ma.emsi.Service;



import ma.emsi.Model.*;
import ma.emsi.Repository.AddressRepository;
import ma.emsi.Repository.OrderRepository;
import ma.emsi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order placeOrder(Integer cartId, Integer addressId, Integer userId) {
        Cart cart = cartService.getCartById(cartId);
        Adress address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order(cart, address, user);
        return orderRepository.save(order);
    }
}
