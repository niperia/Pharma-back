
package ma.emsi.Controller;

import ma.emsi.Model.Order;
import ma.emsi.Model.Enum.PaymentMethod;
import ma.emsi.Model.User;
import ma.emsi.Service.ShoppingService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/create")
    public int createOrder(@RequestBody Order order
                             ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return shoppingService.createOrder(order,user.getId());
    }

}
