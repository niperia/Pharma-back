package ma.emsi.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.Model.Order;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Integer id;
    private String productName; // Assuming Product has a name field
    private String address; // Assuming Adress has a proper toString or address field
    private String userId; // ID of the user who made the order

    private Double totalAmount;

    public OrderResponse(Order order) {
        this.id = order.getId();
//        this.productName = order.getProduct() != null ? order.getProduct().getName() : null; // Get product name
//        this.address = order.getAdress() != null ? order.getAdress().getAdresse() : null; // Get address
//        this.userId = order.getUser() != null ? order.getUser().getFirstName() + " " + order.getUser().getLastName() : null; // Assuming User has an ID field
//        this.totalAmount = order.getProduct().getPrice();
    }

}