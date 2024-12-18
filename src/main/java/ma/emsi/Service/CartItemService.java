package ma.emsi.Service;

import ma.emsi.Model.CartItem;
import ma.emsi.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem getCartItemById(Integer id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
    }

    public CartItem updateCartItem(Integer id, CartItem updatedCartItem) {
        CartItem cartItem = getCartItemById(id);
        cartItem.setQuantity(updatedCartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(Integer id) {
        CartItem cartItem = getCartItemById(id);
        cartItemRepository.delete(cartItem);
    }
}