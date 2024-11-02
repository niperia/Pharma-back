package ma.emsi.Controller;

import ma.emsi.Model.AuthenticationResponse;
import ma.emsi.Model.User;
import ma.emsi.Service.PublicService;
import ma.emsi.dto.Brand.BrandResponse;
import ma.emsi.dto.category.CategoryResponse;
import ma.emsi.dto.product.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicController {
    @Autowired
    private final PublicService publicService;

    public PublicController(PublicService publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>>list_cat(
    ){
        List<CategoryResponse> posts = publicService.getAllCategories();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>>list_pro(  @RequestParam(defaultValue = "") String category
                                                           , @RequestParam(defaultValue = "") String brand,
                                                           @RequestParam( required = false)String maxPrice
    ){
        Double maxPriceValue = null;
        try {
            if (maxPrice != null && !maxPrice.trim().isEmpty()) {
                maxPriceValue = Double.parseDouble(maxPrice);
            }
        } catch (NumberFormatException e) {
            // Log the error or handle it as appropriate
            return ResponseEntity.badRequest().body(null); // or handle the error response in a different way
        }
        List<ProductResponse> posts = publicService.getAllProducts(category,brand,maxPriceValue);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>>list_bra(
    ){
        List<BrandResponse> posts = publicService.getAllBrands();

        return ResponseEntity.ok(posts);
    }
    @GetMapping("/products/{id}") // Corrected to use a path variable
    public ResponseEntity<ProductResponse> getProductById(@PathVariable int id) {
        ProductResponse product = publicService.getProductById(id); // Ensure you implement this method in the service
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the product is not found
        }
    }

}
