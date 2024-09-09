package ma.emsi.Controller;

import ma.emsi.Model.Brand;
import ma.emsi.Model.Category;
import ma.emsi.Service.AdminService;
import ma.emsi.dto.product.ProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<String>DeleteProduct(@PathVariable int id) {
        return ResponseEntity.ok(adminService.DeleteProduct(id));
    }
    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<String>DeleteCategory(@PathVariable int id) {
        return ResponseEntity.ok(adminService.DeleteCategory(id));
    }
}
