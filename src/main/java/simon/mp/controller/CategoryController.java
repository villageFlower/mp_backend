package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simon.mp.APIList;
import simon.mp.dataclass.AddCategoryReq;
import simon.mp.entity.Category;
import simon.mp.service.CategoryService;
import simon.mp.util.Constants;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @GetMapping(APIList.GET_ALL_CETEGORIES)
    public Page<Category> GetAllProducts(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constants.Page_Size) int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return categoryService.getAllCategories(paging);
    }

    @CrossOrigin
    @GetMapping(APIList.GET_ALL_CETEGORIES_LIST)
    public List<Category> GetAllCategories(
    ) {
        return categoryService.getAllCategoriesList();
    }

    @CrossOrigin
    @GetMapping(APIList.GET_CATEGORY_BY_ID)
    public Optional<Category> GetCategoriesById(
            @RequestParam(name = "category_id",required = false) Long category_id
    ) {
        return categoryService.GetCategoryById(category_id);
    }

    @CrossOrigin
    @PostMapping(APIList.ADD_CATEGORY)
    public ResponseEntity<Category> AddCategory(@RequestBody AddCategoryReq req){
        return categoryService.AddCategory(req);
    }

    @CrossOrigin
    @PostMapping(APIList.UPDATE_CATEGORY)
    public ResponseEntity<Category> updateCategory(@RequestBody AddCategoryReq req){
        return categoryService.UpdateCategoryById(req);
    }

    @CrossOrigin
    @PostMapping(APIList.DELETE_CATEGORY)
    public ResponseEntity<String> deleteCategory(@RequestBody AddCategoryReq req){
        return categoryService.DeleteCategoryById(req);
    }
}
