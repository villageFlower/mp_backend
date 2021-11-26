package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import simon.mp.APIList;
import simon.mp.entity.Category;
import simon.mp.entity.Product;
import simon.mp.service.CategoryService;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(APIList.GET_ALL_CETEGORIES)
    public List<Category> GetAllProducts() {
        return categoryService.getAllCategories();
    }
}
