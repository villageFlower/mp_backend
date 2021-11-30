package simon.mp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import simon.mp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}