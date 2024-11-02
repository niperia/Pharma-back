package ma.emsi.Service;



import ma.emsi.Model.*;
import ma.emsi.Model.Enum.PaymentMethod;
import ma.emsi.Repository.AdresseRepo;
import ma.emsi.Repository.OrderRepository;
import ma.emsi.Repository.ProductRepo;
import ma.emsi.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingService {
    private final AdresseRepo adresseRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final OrderRepository orderRepo;

        public ShoppingService(AdresseRepo adresseRepo, UserRepo userRepo,ProductRepo productRepo,OrderRepository orderRepo) {
        this.adresseRepo = adresseRepo;
        this.userRepo = userRepo;
        this.productRepo=productRepo;
        this.orderRepo=orderRepo;
    }

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    // Create an order from a cart
    public int createOrder(Order request,int userid) {
        // Fetch user by ID
        Optional<User> userOptional = userRepo.findById(userid);
        Optional<Adress>adressOptional=adresseRepo.findById(request.getAddressId());
        Optional<Product>productOptional=productRepo.findById(request.getProductId());
        // Validate user existence
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (adressOptional.isEmpty()) {
            throw new IllegalArgumentException("adress not found");
        }

       Order order=new Order();
        order.setAdress(adressOptional.get());
        order.setProduct(productOptional.get());
        order.setUser(userOptional.get());


        // Save and return the ID
        orderRepo.save(order);
        return order.getId();

    }
}
