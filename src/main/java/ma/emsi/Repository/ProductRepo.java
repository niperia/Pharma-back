package ma.emsi.Repository;


import ma.emsi.Model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int id);
    List<Product> findAll(Specification<Product> specification);
}
