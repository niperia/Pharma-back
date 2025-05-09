package ma.emsi.Service;



import ma.emsi.Model.Cart;
import ma.emsi.Model.CartItem;
import ma.emsi.Model.Product;
import ma.emsi.Repository.CartItemRepository;
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
    private CartItemRepository cartItemRepository;

    public Cart getCartById(Integer id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Cart addItemToCart(Integer cartId, CartItem cartItem) {
        Cart cart = getCartById(cartId);
        cart.addItem(cartItem);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Integer cartId, Integer itemId) {
        Cart cart = getCartById(cartId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        cart.removeItem(item);
        cartItemRepository.delete(item);
        return cartRepository.save(cart);
    }

    public Double getCartTotal(Integer cartId) {
        Cart cart = getCartById(cartId);
        return cart.getTotalPrice();
    }
}