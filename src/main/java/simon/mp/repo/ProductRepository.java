package simon.mp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import simon.mp.entity.Category;
import simon.mp.entity.Product;
import simon.mp.util.Constants;


public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product WHERE stock > 0 ORDER BY created DESC",
            countQuery = "SELECT count(*) FROM product category_id = ?1",
            nativeQuery = true)
    Page<Product> findAllByOrderByCreatedDesc(Pageable pageable);

    @Query(value = "SELECT * FROM product WHERE category_id = ?1 AND stock > 0",
            countQuery = "SELECT count(*) FROM product category_id = ?1",
            nativeQuery = true)
    Page<Product> findAllByCategory(Long category, Pageable pageable);

}