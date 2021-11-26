package simon.mp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import simon.mp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}