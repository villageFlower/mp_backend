package simon.mp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import simon.mp.entity.Address;
import simon.mp.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM `order` WHERE user_id = ?1",
            nativeQuery = true)
    List<Order> findByUserid(Long user_id);
}