package ma.emsi.Repository;

import ma.emsi.Model.Brand;
import ma.emsi.Model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepo extends JpaRepository<Brand, Integer> {
    Optional<Brand> findById(Integer integer);

}
