package ma.emsi.Controller;

import ma.emsi.Model.Brand;
import ma.emsi.Model.Category;
import ma.emsi.Model.Order;
import ma.emsi.Service.AdminService;
import ma.emsi.dto.product.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin/brand")
    public ResponseEntity<String> addMarque
            (@RequestBody Brand brand) {
        {
            return ResponseEntity.ok(adminService.AddBrand(brand));
        }
    }
    @PostMapping("/admin/category")
    public ResponseEntity<String> addCategory
            (@RequestBody Category category) {
        {
            return ResponseEntity.ok(adminService.AddCategory(category));
        }
    }
    @PostMapping("/admin/product")
    public ResponseEntity<String> addProduct
            (@ModelAttribute ProductRequest productReq) {
        {
            return ResponseEntity.ok(adminService.AddProduct(productReq));
        }
    }
    @PutMapping("/admin/product/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable int id,
            @ModelAttribute ProductRequest productReq) {
        String response = adminService.updateProduct(id, productReq);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<String>DeleteProduct(@PathVariable int id) {
        return ResponseEntity.ok(adminService.DeleteProduct(id));
    }
    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<String>DeleteCategory(@PathVariable int id) {
        return ResponseEntity.ok(adminService.DeleteCategory(id));
    }
    @DeleteMapping("/admin/brand/{id}")
    public ResponseEntity<String>DeleteBrand(@PathVariable int id) {
        return ResponseEntity.ok(adminService.Deletebrand(id));
    }
    @GetMapping("/admin/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ma.emsi.dto.Order.OrderResponse>> getAllOrders() {
        List<Order> orders = adminService.getAllOrders(); // Fetch Order entities
        List<ma.emsi.dto.Order.OrderResponse> orderResponses = orders.stream()
                .map(order -> new ma.emsi.dto.Order.OrderResponse(order)) // Map to DTO
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderResponses);
    }


}
