package simon.mp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import simon.mp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAllByOrderByArrange(Pageable pageable);

}