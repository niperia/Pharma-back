package ma.emsi.Controller;



import ma.emsi.Model.Cart;
import ma.emsi.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public Cart createCart() {
        return cartService.createCart();
    }

    @PostMapping("/{cartId}/add")
    public String addToCart(@PathVariable Integer cartId, @RequestParam Integer productId, @RequestParam int quantity) {
        Optional<Cart> cartOptional = cartService.getCartById(cartId);
        if (cartOptional.isPresent()) {
            cartService.addProductToCart(cartOptional.get(), productId, quantity);
            return "Product added to cart!";
        } else {
            return "Cart not found!";
        }
    }

    @GetMapping("/{cartId}/total")
    public Double getCartTotalPrice(@PathVariable Integer cartId) {
        Optional<Cart> cartOptional = cartService.getCartById(cartId);
        if (cartOptional.isPresent()) {
            return cartService.getCartTotalPrice(cartOptional.get());
        } else {
            return 0.0;
        }
    }
}
