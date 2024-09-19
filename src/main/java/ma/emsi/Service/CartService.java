package ma.emsi.Service;



import ma.emsi.Model.Cart;
import ma.emsi.Model.CartItem;
import ma.emsi.Model.Product;
import ma.emsi.Repository.CartRepository;
import ma.emsi.Repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepo productRepository;

    // Create or get the cart (For simplicity, we're assuming one cart per session)
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    // Add product to cart
    public void addProductToCart(Cart cart, Integer productId, int quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            CartItem cartItem = new CartItem(product, quantity);
            cart.addItem(cartItem);
            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    // Get total price of cart
    public Double getCartTotalPrice(Cart cart) {
        return cart.getTotalPrice();
    }

    // Get cart by ID
    public Optional<Cart> getCartById(Integer cartId) {
        return cartRepository.findById(cartId);
    }
}
