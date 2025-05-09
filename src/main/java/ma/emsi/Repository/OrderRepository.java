package ma.emsi.Repository;

import ma.emsi.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Find orders by user
    List<Order> findByUserId(Integer userId);

    // Find orders by date range
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

}
