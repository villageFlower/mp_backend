package simon.mp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import simon.mp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}