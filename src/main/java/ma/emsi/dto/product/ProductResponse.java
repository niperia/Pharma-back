package ma.emsi.dto.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.Model.Brand;
import ma.emsi.Model.Category;
import ma.emsi.Model.Product;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
  public ProductResponse(Product product) {
      this.id=product.getId();
      this.name=product.getName();
      this.brand=product.getBrand();
      this.description=product.getDescription();
      this.price=product.getPrice();
      this.category=product.getCategory();
      this.quantity=product.getQuantity();
      this.picture=product.getPicture();
      this.createdAt=product.getCreatedAt();
      this.updatedAt=product.getUpdatedAt();
      this.discountValue=product.getDiscountValue();
      this.tags=product.getTags();
  }
    private Integer id;
    private Brand brand;
    private String name;

    private Double price;

    private int quantity;


    private Category category;

    private String picture;

    private String description;

    private Double discountValue;

    private String tags;

    private Date createdAt;

    private Date updatedAt;
}
