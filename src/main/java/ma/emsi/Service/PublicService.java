package ma.emsi.Service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import ma.emsi.Model.Brand;
import ma.emsi.Model.Category;
import ma.emsi.Model.Product;
import ma.emsi.Repository.BrandRepo;
import ma.emsi.Repository.CategoryRepo;
import ma.emsi.Repository.ProductRepo;
import ma.emsi.dto.Brand.BrandResponse;
import ma.emsi.dto.category.CategoryResponse;
import ma.emsi.dto.product.ProductResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicService {
    private final CategoryRepo repository;
    private final ProductRepo repositoryProduct;
    private final BrandRepo repositoryBrand;

    public PublicService(CategoryRepo repository, ProductRepo repositoryProduct, BrandRepo repositoryBrand) {
        this.repository = repository;
        this.repositoryProduct = repositoryProduct;
        this.repositoryBrand = repositoryBrand;
    }

    public List<BrandResponse> getAllBrands() {
        List<Brand> ls = repositoryBrand.findAll();
        return ls.stream()
                .map(BrandResponse::new)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> ls = repository.findAll();
        return ls.stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getAllProducts(String categorySlug, String brand, Double maxPrice) {
        List<Product> ls;
        ls = repositoryProduct.findAll((Specification<Product>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<Product, Category> categoryJoin = root.join("category");
            Join<Product, Brand> brandJoin = root.join("brand");

            // Filter by category
            if (categorySlug != null && !categorySlug.isEmpty()) {
                System.out.println("Filtering by categorySlug: " + categorySlug);
                predicates.add(criteriaBuilder.equal(categoryJoin.get("slug"), categorySlug));
            }

            if (brand != null && !brand.isEmpty()) {
                System.out.println("Filtering by brandName: " + brand);
                predicates.add(criteriaBuilder.equal(brandJoin.get("id"), brand));
            }

            if (maxPrice != null) {
                System.out.println("Filtering by maxPrice: " + maxPrice);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            } else {
                System.out.println("maxPrice is null or empty, skipping filter.");
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        return ls.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());

}
}