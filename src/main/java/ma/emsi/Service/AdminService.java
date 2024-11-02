package ma.emsi.Service;

import ma.emsi.Model.Brand;
import ma.emsi.Model.Category;
import ma.emsi.Model.Order;
import ma.emsi.Model.Product;
import ma.emsi.Repository.BrandRepo;
import ma.emsi.Repository.CategoryRepo;
import ma.emsi.Repository.OrderRepository;
import ma.emsi.Repository.ProductRepo;
import ma.emsi.dto.product.ProductRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminService {
    private final CategoryRepo repository;
    private final ProductRepo repositoryProduct;
    private final ImgService imgService;
    private final BrandRepo brandRepo;
    private final OrderRepository orderRepository;
    public AdminService(CategoryRepo repository, ProductRepo repositoryProduct, ImgService imgService, BrandRepo brandRepo, OrderRepository orderRepository) {
        this.repository = repository;
        this.repositoryProduct = repositoryProduct;
        this.imgService = imgService;
        this.brandRepo = brandRepo;
        this.orderRepository = orderRepository;
    }

    public String AddCategory(Category request) {

        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category=repository.save(category);
        return "ok success";
    }
    public String AddProduct(ProductRequest productReq) {
        Product product = new Product();
        MultipartFile P =productReq.picture();
        String imgfilename=imgService.addimage(P,"Products");
        product.setBrand(brandRepo.findById(productReq.brand()).orElse(null));
        product.setPicture(imgfilename);
        product.setName(productReq.name());
        product.setCategory(repository.findById(productReq.category()).orElse(null));
        product.setDescription(productReq.description());
        product.setPrice(productReq.price());
        product.setQuantity(productReq.quantity());
        product.setTags(productReq.tags());
        product.setDiscountValue(productReq.discountValue());
        product=repositoryProduct.save(product);
        return "ok success";
    }
    public String AddBrand(Brand request){
        Brand brand=new Brand();
        brand.setName(request.getName());
        brand.setSlug(request.getSlug());
        brand=brandRepo.save(brand);
        return "ok success";
    }
    public String DeleteProduct(int id) {
        repositoryProduct.deleteById(id);
        return "deleted.";
    }
    public String DeleteCategory(int id) {
        repository.deleteById(id);
        return "deleted.";
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll(); // Fetch all orders
    }

}
