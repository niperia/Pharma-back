package ma.emsi.Repository;

import ma.emsi.Model.Category;
import ma.emsi.Model.Product;
import ma.emsi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findById(int id);
}
