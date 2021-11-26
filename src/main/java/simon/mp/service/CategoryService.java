package simon.mp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import simon.mp.dataclass.AddCategoryReq;
import simon.mp.entity.Category;
import simon.mp.entity.Image;
import simon.mp.repo.CategoryRepository;
import simon.mp.repo.ImageRepository;
import simon.mp.util.Constants;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImageRepository imageRepository;

    Pageable defaultPage = PageRequest.of(1, Constants.Page_Size_Int);

    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public Page<Category> getAllCategories(Pageable page) {
        return categoryRepository.findAllByOrderByArrange(page);
    }

    public List<Category> getAllCategoriesList() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> AddCategory(AddCategoryReq req) {
        Image image = imageRepository.findById(req.image_id).orElse(null);
        if (image == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Category());
        }
        Category category = new Category();
        category.setName(req.name);
        category.setActive(req.active);
        category.setArrange(req.arrange);
        category.setIcon(image);

        Category result = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public ResponseEntity<Category> UpdateCategoryById(AddCategoryReq categoryReq){
        Category category = categoryRepository.findById(categoryReq.id).orElse(null);
        if (category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category());
        }
        category.setName(categoryReq.name);
        category.setArrange(categoryReq.arrange);
        category.setActive(categoryReq.active);
        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    public ResponseEntity<String> DeleteCategoryById(AddCategoryReq categoryReq){
        Category category = categoryRepository.findById(categoryReq.id).orElse(null);
        if (category == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed");
        }
        categoryRepository.delete(category);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
