package simon.mp.controller;

import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simon.mp.APIList;
import simon.mp.dataclass.AddCategoryReq;
import simon.mp.dataclass.AddProductReq;
import simon.mp.dataclass.UpdateProductReq;
import simon.mp.entity.Image;
import simon.mp.entity.Product;
import simon.mp.entity.QProduct;
import simon.mp.repo.ProductRepository;
import simon.mp.service.ProductService;
import simon.mp.util.Constants;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    public static QProduct Qproduct = QProduct.product;

    @GetMapping(APIList.GET_ALL_PRODUCTS)
    public Page<Product> GetAllProducts(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constants.Page_Size) int size,
            @RequestParam(name = "category_id",required = false) Long category_id
    ) {
        Pageable paging = PageRequest.of(page, size);
        return productService.getAllProducts(paging,category_id);
    }
    @GetMapping(APIList.DELETE_PRODUCT_BY_ID)
    public ResponseEntity<String> DeleteProductById(
            @RequestParam(name = "id",required = true) Long id
    ) {
        return productService.deleteProductById(id);
    }

    @PostMapping(APIList.ADD_PRODUCT)
    public Product addProduct(@RequestBody AddProductReq req){
        return productService.addProduct(req);
    }

    @GetMapping(APIList.GET_PRODUCT_BY_ID)
    public Product getProduct(@RequestParam(required = false) long id){
        return productService.getProductById(id);
    }

    @PostMapping(APIList.UPDATE_PRODUCT)
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProductReq req){
        return productService.editProduct(req);
    }

}
