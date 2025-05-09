package ma.emsi.Controller;



import ma.emsi.Model.Cart;
import ma.emsi.Model.CartItem;
import ma.emsi.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Integer id) {
        return cartService.getCartById(id);
    }

    @PostMapping
    public Cart createCart() {
        return cartService.createCart();
    }

    @PostMapping("/{cartId}/add-item")
    public Cart addItemToCart(@PathVariable Integer cartId, @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(cartId, cartItem);
    }

    @DeleteMapping("/{cartId}/remove-item/{itemId}")
    public Cart removeItemFromCart(@PathVariable Integer cartId, @PathVariable Integer itemId) {
        return cartService.removeItemFromCart(cartId, itemId);
    }

    @GetMapping("/{id}/total")
    public Double getTotalPrice(@PathVariable Integer id) {
        return cartService.getCartTotal(id);
    }
}