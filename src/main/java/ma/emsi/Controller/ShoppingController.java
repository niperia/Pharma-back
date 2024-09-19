
package ma.emsi.Controller;

import ma.emsi.Model.Order;
import ma.emsi.Model.Enum.PaymentMethod;
import ma.emsi.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/create")
    public Order createOrder(@RequestParam Integer cartId, @RequestParam String address,
                             @RequestParam PaymentMethod paymentMethod) {
        return shoppingService.createOrder(cartId, address, paymentMethod);
    }
}
