package ma.emsi.Controller;

import ma.emsi.Model.CartItem;
import ma.emsi.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Integer id) {
        return cartItemService.getCartItemById(id);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable Integer id, @RequestBody CartItem cartItem) {
        return cartItemService.updateCartItem(id, cartItem);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Integer id) {
        cartItemService.deleteCartItem(id);
    }
}
