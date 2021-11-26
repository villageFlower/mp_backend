package simon.mp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simon.mp.dto.AddCategoryReq;
import simon.mp.entity.AdminUser;
import simon.mp.entity.Category;
import simon.mp.entity.Product;
import simon.mp.repo.CategoryRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> AddCategory(AddCategoryReq req) {
        return ResponseEntity.status(HttpStatus.OK).body(new Category());
    }
}
