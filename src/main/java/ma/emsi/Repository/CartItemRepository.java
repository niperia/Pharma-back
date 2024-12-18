package ma.emsi.Repository;

import ma.emsi.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    // Custom query methods (if needed) can be added here.
}
