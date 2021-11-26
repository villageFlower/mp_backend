package simon.mp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import simon.mp.entity.Category;
import simon.mp.entity.Product;
import simon.mp.util.Constants;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByCreatedDesc(Pageable pageable);
}